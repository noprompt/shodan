## Changes between 0.1.0 and 0.2.0

Rename `shodan.console/group` to `shodan.console/group-start`

Rename `shodan.console/group-collpased` to
`shodan.console/group-start-collapsed`

Rename `shodan.console/profile` to `shodan.console/profile-start`

Rename typo `shodan.console/with-group-collpased` to
`shodan.console/with-group-collapsed` (typo)

Move `shodan.console.macros/console` to `shodan.console`

Fix bug in `shodan.console/group-end` which would return
the value of `console.groupEnd` instead of calling `console.groupEnd`

