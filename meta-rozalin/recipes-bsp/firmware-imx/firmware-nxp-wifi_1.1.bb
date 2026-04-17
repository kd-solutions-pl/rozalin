# Copyright 2020-2026 NXP

SUMMARY = "Wi-Fi firmware redistributed by NXP"
DESCRIPTION = "Additional Wi-Fi firmware redistributed by NXP. Some \
is available in linux-firmware, but what is here is the latest and \
should be preferred."

SECTION = "kernel"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=bc649096ad3928ec06a8713b8d787eac"
LICENSE_FLAGS = "NXP_EULA_v63"
LICENSE_FLAGS_DETAILS[NXP_EULA_v63] = "For further details, see ${THISDIR}/files/NXP_EULA_v63."

SRC_URI = "git://github.com/nxp-imx/imx-firmware.git;protocol=https;branch=${SRCBRANCH}"
SRCBRANCH = "lf-6.18.2_1.0.0"
SRCREV = "d7e4bb37b45bbf93faf888e0ca6763a29e28054a"


inherit allarch

do_compile[noexec] = "1"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/nxp
    oe_runmake install INSTALLDIR=${D}${nonarch_base_libdir}/firmware/nxp
}

PACKAGES =+ " \
    ${PN}-nxpiw612-sdio \
"

FILES:${PN}-nxpiw612-sdio = " \
    ${nonarch_base_libdir}/firmware/nxp/sd_w61x_v1.bin.se \
    ${nonarch_base_libdir}/firmware/nxp/sduart_nw61x_*.bin.se \
    ${nonarch_base_libdir}/firmware/nxp/uartspi_n61x_*.bin.se \
    ${nonarch_base_libdir}/firmware/nxp/uartuart_n61x_*.bin.se \
    ${nonarch_base_libdir}/firmware/nxp/IW612_SD_RFTest/ \
    ${nonarch_base_libdir}/firmware/nxp/wifi_mod_para.conf \
    ${nonarch_base_libdir}/firmware/nxp/helper_uart_3000000.bin \
"

FILES:${PN} = "${nonarch_base_libdir}/firmware/nxp/*"
