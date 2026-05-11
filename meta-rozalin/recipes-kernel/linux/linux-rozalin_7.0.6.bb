LICENSE = "GPL-2.0-only"

inherit kernel

SRC_URI = "${KERNELORG_MIRROR}/linux/kernel/v7.x/linux-${PV}.tar.xz"
SRC_URI[sha256sum] = "cba44440aa57affd7c21241dc5bc234b0df53c499f8ffc3ebc290dd3390a7523"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
S = "${UNPACKDIR}/linux-${PV}"

SRC_URI:append:imx93frdm = " \
    file://0001-linux-rozalin-Add-support-for-optee-in-imx93frdm.patch \
    "
