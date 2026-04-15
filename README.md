
# Rozalin
Rozalin is a toy project for my personal research and development tasks. I will update this repository from time to time. I'm particularly interested in the idea of ​​rebasing it based on the latest yocto/meta-arm releases or removing the meta-arm dependency after the (potential?) merge of optee-os with openembedded-core.

I started work inspired by [Bootlin implementation](https://github.com/bootlin/simplest-yocto-setup) and adapted it to

  * yocto "wrynose"
  * systemd (as default init)
  * linux kernel 7.0.1
  * u-boot v2026.04
  * OP-TEE 4.9.0
  * Latest imx firmware files
  * Simple Access Point configuration

## Host tools

```Bash
    $ pip3 install kas
```

## Fetch
```Bash
    $ git clone https://github.com/kd-solutions-pl/rozalin.git
    $ cd rozalin
    $ kas checkout
```

## Configure
```Bash
    $ . openembedded-core/oe-init-build-env
    $ echo 'LICENSE_FLAGS_ACCEPTED += "NXP_EULA_v62 NXP_EULA_v63"' >> conf/site.conf
    $ echo 'MACHINE = "imx93frdm"' >> conf/site.conf
```

For development purposes add following

```Bash
    $ echo 'EXTRA_IMAGE_FEATURES = "allow-empty-password allow-root-login empty-root-password"' >> conf/site.conf
```

but keep in mind that above settings are not recommended for production environment!

## Build

```Bash
    $ bitbake rozalin-image
```

## Flash

Program MMC card

```Bash
    $ bmaptool copy tmp/deploy/images/imx93frdm/rozalin-image-imx93frdm.rootfs.wic <your MMC device>
```

## Example of runtime configuration
### Access Point with static IP
```Bash
    root@imx93frdm:~# ip address add 192.168.50.1/24 dev uap0
    root@imx93frdm:~# iw reg set PL
    root@imx93frdm:~# systemctl start hostapd.service
```

Enjoy!

[www.kd-solutions.pl](www.kd-solutions.pl)
