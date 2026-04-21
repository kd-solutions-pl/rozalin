SUMMARY = "User space daemon for extended IEEE 802.11 management"
HOMEPAGE = "http://w1.fi/hostapd/"
SECTION = "kernel/userland"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://hostapd/README;beginline=5;endline=47;md5=8e2c69e491b28390f9de0df1f64ebd6d"

DEPENDS = "libnl openssl"

SRC_URI = " \
    git://git.w1.fi/hostap.git;protocol=https;branch=main \
    file://defconfig \
    file://hostapd.service \
    file://hostapd.conf \
"
SRCREV = "80b280f74255a6269645462dde12b9bbd34927c1"
inherit systemd pkgconfig features_check

CONFLICT_DISTRO_FEATURES = "openssl-no-weak-ciphers"

INITSCRIPT_NAME = "hostapd"

SYSTEMD_SERVICE:${PN} = "hostapd.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

do_configure:append() {
    install -m 0644 ${UNPACKDIR}/defconfig ${B}/hostapd/.config
}

do_compile() {
    export CFLAGS="-MMD -O2 -Wall -g"
    export EXTRA_CFLAGS="${CFLAGS}"
    make -C hostapd V=1
}

do_install() {
    install -d ${D}${sbindir} ${D}${sysconfdir}/init.d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/hostapd.conf ${D}${sysconfdir}
    install -m 0755 ${B}/hostapd/hostapd ${D}${sbindir}
    install -m 0755 ${B}/hostapd/hostapd_cli ${D}${sbindir}
    install -m 0644 ${UNPACKDIR}/hostapd.service ${D}${systemd_system_unitdir}
    sed -i -e 's,@SBINDIR@,${sbindir},g' -e 's,@SYSCONFDIR@,${sysconfdir},g' ${D}${systemd_system_unitdir}/hostapd.service
}

CONFFILES:${PN} += "${sysconfdir}/hostapd.conf"
