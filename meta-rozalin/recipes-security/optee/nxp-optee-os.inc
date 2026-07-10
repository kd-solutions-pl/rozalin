require recipes-security/optee/optee-os.inc

SRC_URI = "git://github.com/nxp-imx/imx-optee-os.git;protocol=https;branch=lf-6.12.49_2.2.0"
SRCREV = "b3883a773a9d15ec6439f9229e48f540c37e0d00"

OPTEEMACHINE = "imx"
EXTRA_OEMAKE:append = " PLATFORM=imx-mx91evk"

COMPATIBLE_MACHINE = "(^imx91sfrdm$)"
