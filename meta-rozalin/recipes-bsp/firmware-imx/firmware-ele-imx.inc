# Copyright 2021-2024 NXP
SUMMARY = "NXP i.MX ELE firmware"
DESCRIPTION = "EdgeLock Secure Enclave firmware for i.MX series SoCs"
SECTION = "base"

LICENSE = "Proprietary"
NXP_FIRMWARE_ARCHIVE = "${BP}-${IMX_SRCREV_ABBREV}"
NXP_FIRMWARE_ARCHIVE_BIN = "${NXP_FIRMWARE_ARCHIVE}.bin"
SRC_URI = "https://www.nxp.com/lgfiles/NMG/MAD/YOCTO/${NXP_FIRMWARE_ARCHIVE_BIN};fsl-eula=true"

S = "${UNPACKDIR}/${NXP_FIRMWARE_ARCHIVE}"

python do_unpack:append() {
    cmd = "sh %s --auto-accept --force" % d.getVar('NXP_FIRMWARE_ARCHIVE_BIN')
    bb.process.run(cmd, shell=True, cwd=d.getVar('UNPACKDIR', True))
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/imx/ele
    install -m 0644 ${S}/${SECO_FIRMWARE_NAME} ${D}${nonarch_base_libdir}/firmware/imx/ele
}

FILES:${PN} += "${nonarch_base_libdir}/firmware/imx/ele/${SECO_FIRMWARE_NAME}"

COMPATIBLE_MACHINE = "(^imx93frdm$|^imx91sfrdm$)"
