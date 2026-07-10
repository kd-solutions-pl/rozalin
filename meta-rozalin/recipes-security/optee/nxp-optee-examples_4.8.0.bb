require recipes-security/optee/optee-examples.inc

PROVIDES += "optee-examples"

# v4.8.0, matching the OP-TEE baseline used by NXP lf-6.12.49_2.2.0.
SRCREV = "3ef17eb1f309def91113637f95f67613b1d89119"

COMPATIBLE_MACHINE = "(^imx91sfrdm$)"
