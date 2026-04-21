SUMMARY = "A simple, minimal image"

IMAGE_INSTALL = "packagegroup-core-boot"

IMAGE_LINGUAS = " "

inherit core-image

IMAGE_INSTALL:append:imx93frdm = " hostapd wireless-regdb-static iw"
