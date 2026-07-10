SUMMARY = "Factory provisioning tools for Rozalin boards"
LICENSE = "CLOSED"

UUU_SCRIPT = "imx91s_frdm_flash_nand.uuu"

SRC_URI = "file://${UUU_SCRIPT}"

S = "${UNPACKDIR}"

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${UNPACKDIR}/${UUU_SCRIPT} ${DEPLOYDIR}/${UUU_SCRIPT}
}

addtask deploy after do_install before do_populate_sysroot do_build

COMPATIBLE_MACHINE = "(^imx91sfrdm$)"
