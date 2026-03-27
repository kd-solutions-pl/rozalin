LICENSE = "GPL-2.0-only"

inherit kernel

SRC_URI = "${KERNELORG_MIRROR}/linux/kernel/v7.x/linux-${PV}.tar.xz"
SRC_URI[sha256sum] = "bb7f6d80b387c757b7d14bb93028fcb90f793c5c0d367736ee815a100b3891f0"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
S = "${UNPACKDIR}/linux-${PV}"
