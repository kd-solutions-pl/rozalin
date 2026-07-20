LICENSE = "GPL-2.0-only"

inherit kernel

SRC_URI = "${KERNELORG_MIRROR}/linux/kernel/v7.x/linux-${PV}.tar.xz"
SRC_URI[sha256sum] = "1c63922a119675d38e3ae0f8f6ee07f15c41a786ab9ed66563749bb8c9a08e2e"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
S = "${UNPACKDIR}/linux-${PV}"

SRC_URI:append:imx93frdm = " \
    file://0001-linux-rozalin-Add-support-for-optee-in-imx93frdm.patch \
    "
SRC_URI:append:imx91sfrdm = " \
    file://defconfig \
    file://0001-linux-rozalin-Add-support-for-optee-in-imx91s-frdm.patch \
    "
