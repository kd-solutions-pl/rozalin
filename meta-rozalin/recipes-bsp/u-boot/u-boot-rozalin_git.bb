require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "gnutls-native"
# v2026.07
SRC_URI = "git://source.denx.de/u-boot/u-boot.git;protocol=https;branch=master"
SRCREV = "ece349ade2973e220f524ce59e59711cc919263f"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SRC_URI:append:imx93frdm = " \
    file://0001-imx93-Add-support-for-OPTEE.patch \
    file://optee.cfg \
    "
SRC_URI:append:imx91sfrdm = " \
    file://optee.cfg \
    file://0001-power-pmic-ADD-PMIC-PF9453-support.patch \
    file://0002-power-regulator-ADD-PMIC-PF9453-support.patch \
    file://0003-arm-dts-imx91-Add-SAI1-and-MQS1-nodes.patch \
    file://0004-arm-dts-imx91-Add-FRDM-S-device-tree.patch \
    file://0005-LFU-568-new-tool-to-support-i.MX9-spi-nand-u_boot.patch \
    file://0006-imx9-Introduce-CONFIG_IMX9_NAND_2K_BOOT-option.patch \
    file://0007-imx9-Add-support-for-booting-from-2k-NAND-flash.patch \
    file://0008-imx-spl-align-ROM-API-reads.patch \
    file://0010-imx91s_frdm-Add-board-support.patch \
    file://0011-power-Fix-access-to-LDO-SNVS.patch \
    "

# We will embed boot firmwares, in the generated binary: we do depend on them.
DEPENDS:append:imx93frdm = " optee-os trusted-firmware-a imx-boot-firmware-files firmware-ele-imx"
DEPENDS:append:imx91sfrdm = " optee-os trusted-firmware-a imx-boot-firmware-files firmware-ele-imx"

EXTRA_OEMAKE:append:imx93frdm = " BINMAN_INDIRS=${RECIPE_SYSROOT}/firmware"
EXTRA_OEMAKE:append:imx91sfrdm = " BINMAN_INDIRS=${RECIPE_SYSROOT}/firmware"

do_configure:append:imx93frdm() {
    # Copy ele firmware binaries in build directory, so they can be found by mkiage
     config="${@ d.getVar('UBOOT_MACHINE').strip()}"
     type="${@ d.getVar('UBOOT_CONFIG').strip()}"
     if [ -n ${type} ]; then
        config=${config}-${type}
     fi
     cp ${RECIPE_SYSROOT}/firmware/trusted-firmware-a/bl31.bin ${B}/${config}
     cp ${RECIPE_SYSROOT}/${nonarch_base_libdir}/firmware/imx/ele/${SECO_FIRMWARE_NAME} ${B}/${config}
     cp ${RECIPE_SYSROOT}/${nonarch_base_libdir}/firmware/tee-raw.bin ${B}/${config}/tee.bin
}

do_configure:append:imx91sfrdm() {
    # Copy ele firmware binaries in build directory, so they can be found by mkiage
     config="${@ d.getVar('UBOOT_MACHINE').strip()}"
     type="${@ d.getVar('UBOOT_CONFIG').strip()}"
     if [ -n ${type} ]; then
        config=${config}-${type}
     fi
     cp ${RECIPE_SYSROOT}/firmware/trusted-firmware-a/bl31.bin ${B}/${config}
     cp ${RECIPE_SYSROOT}/${nonarch_base_libdir}/firmware/imx/ele/${SECO_FIRMWARE_NAME} ${B}/${config}
     cp ${RECIPE_SYSROOT}/${nonarch_base_libdir}/firmware/tee-raw.bin ${B}/${config}/tee.bin
}

do_deploy:append:imx91sfrdm() {
    config="${@ d.getVar('UBOOT_MACHINE').strip()}"
    type="${@ d.getVar('UBOOT_CONFIG').strip()}"
    if [ -n ${type} ]; then
       config=${config}-${type}
    fi

    install -m 0644 ${B}/${config}/flash.bin  ${DEPLOYDIR}/flash.bin
}

do_deploy:append:imx93frdm() {
    config="${@ d.getVar('UBOOT_MACHINE').strip()}"
    type="${@ d.getVar('UBOOT_CONFIG').strip()}"
    if [ -n ${type} ]; then
       config=${config}-${type}
    fi

    install -m 0644 ${B}/${config}/flash.bin  ${DEPLOYDIR}/flash.bin
    # From meta-freescale uuu_bootloader_tag.bbclass
    # Create a tagged boot partition file for the SD card image file. The tag
    # contains the size of the boot partition image so UUU can easily find
    # the end of it in the SD card image file.
    #
    # IMPORTANT: The tagged boot partition file should never be used directly with
    #            UUU, as it can cause UUU to hang.
    cp ${DEPLOYDIR}/flash.bin ${DEPLOYDIR}/flash.bin.tagged
    stat -L -cUUUBURNXXOEUZX7+A-XY5601QQWWZ%sEND ${DEPLOYDIR}/flash.bin.tagged >> ${DEPLOYDIR}/flash.bin.tagged
}
