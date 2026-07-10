require nxp-optee-os.inc

SUMMARY = "OP-TEE Trusted OS TA devkit"
DESCRIPTION = "OP-TEE TA devkit for build TAs"
HOMEPAGE = "https://www.op-tee.org/"

PROVIDES += "optee-os-tadevkit"

DEPENDS += "python3-pycryptodome-native"
DEPENDS:append:toolchain-clang = " lld-native"

do_install() {
    # install TA devkit
    install -d ${D}${includedir}/optee/export-user_ta/
    for f in ${B}/export-ta_${OPTEE_ARCH}/* ; do
        cp -aR $f ${D}${includedir}/optee/export-user_ta/
    done
}

do_deploy() {
    echo "Do not inherit do_deploy from optee-os."
}

FILES:${PN} = "${includedir}/optee/"

# Build paths are currently embedded.
INSANE_SKIP:${PN}-dev += "buildpaths"

# Include extra headers needed by SPMC tests to TA DEVKIT.
EXTRA_OEMAKE:append = "${@bb.utils.contains('MACHINE_FEATURES', 'optee-spmc-test', \
                                        ' CFG_SPMC_TESTS=y', '' , d)}"
