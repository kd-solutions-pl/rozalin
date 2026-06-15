LICENSE = "GPL-2.0-only"

inherit kernel

SRC_URI = "${KERNELORG_MIRROR}/linux/kernel/v7.x/linux-${PV}.tar.xz"
SRC_URI[sha256sum] = "691f44797fbe790dc8a321604c927087526ad27b6d649925d60f8eed0a2564a0"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
S = "${UNPACKDIR}/linux-${PV}"

SRC_URI:append:imx93frdm = " \
    file://0001-linux-rozalin-Add-support-for-optee-in-imx93frdm.patch \
    "
