require recipes-bsp/trusted-firmware-a/trusted-firmware-a.inc

PROVIDES += "trusted-firmware-a"

# Keep the sysroot/deploy layout expected by U-Boot.
FIRMWARE_DIR = "${FIRMWARE_BASE_DIR}/trusted-firmware-a"

SRC_URI_TRUSTED_FIRMWARE_A = "gitsm://github.com/nxp-imx/imx-atf.git;protocol=https"
SRCBRANCH = "lf_v2.14"
SRCREV_tfa = "0779f89a5475a03193f7707f3bbb50cec11707c0"

LIC_FILES_CHKSUM += "file://docs/license.rst;md5=6ed7bace7b0bc63021c6eba7b524039e"

TFA_PLATFORM = "imx91"
TFA_BUILD_TARGET = "bl31"
TFA_SPD = "opteed"

COMPATIBLE_MACHINE = "(^imx91sfrdm$)"
