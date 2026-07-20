# Copyright (C) 2012-2016 Freescale Semiconductor
# Copyright 2017-2024 NXP
# Copyright (C) 2018 O.S. Systems Software LTDA.
SUMMARY = "Freescale i.MX Firmware files used for boot"
SECTION = "base"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=bc649096ad3928ec06a8713b8d787eac"
LICENSE_FLAGS = "NXP_EULA_v63"
LICENSE_FLAGS_DETAILS[NXP_EULA_v63] = "For further details, see ${THISDIR}/files/NXP_EULA_v63"


IMX_SRCREV_ABBREV = "4fa5b46"
NXP_FIRMWARE_ARCHIVE = "firmware-imx-${PV}-${IMX_SRCREV_ABBREV}"
NXP_FIRMWARE_ARCHIVE_BIN = "${NXP_FIRMWARE_ARCHIVE}.bin"
SRC_URI = "https://www.nxp.com/lgfiles/NMG/MAD/YOCTO/${NXP_FIRMWARE_ARCHIVE_BIN};fsl-eula=true"
SRC_URI[sha256sum] = "7f83731cae7056ea4007bf8f0b19734cd76d53affb258405c85223ba7ade5354"

S = "${UNPACKDIR}/${NXP_FIRMWARE_ARCHIVE}"

inherit deploy nopackages

python do_unpack:append() {
    cmd = "sh %s --auto-accept --force" % d.getVar('NXP_FIRMWARE_ARCHIVE_BIN')
    bb.process.run(cmd, shell=True, cwd=d.getVar('UNPACKDIR', True))
}

DDR_FIRMWARE_NAME = " \
    lpddr4_dmem_1d_v202201.bin \
    lpddr4_dmem_2d_v202201.bin \
    lpddr4_imem_1d_v202201.bin \
    lpddr4_imem_2d_v202201.bin \
"

do_deploy() {
    for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
        install -m 0644 ${S}/firmware/ddr/synopsys/${ddr_firmware} ${DEPLOYDIR}
    done
}

addtask deploy after do_install before do_build

do_install () {
    install -d ${D}/firmware
    for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
        install -m 0644 ${S}/firmware/ddr/synopsys/${ddr_firmware} ${D}/firmware
    done
}

SYSROOT_DIRS += "/firmware"

COMPATIBLE_MACHINE = "(^imx93frdm$|^imx91sfrdm$)"
