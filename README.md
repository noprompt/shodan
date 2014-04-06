# Shodan

A ClojureScript library providing wrappers for the JavaScript
`console` API.

## Contents
- [Installation](#installation)
- [Usage](#usage)
- [Macros](#macros)
- [Why?](#why)

## "Installation"

Add Shodan as a `:dependency` in your `project.clj` file.

```clojure
[shodan "0.2.0"]
```

## Usage

Console wrappers are in the `shodan.console` namespace.

```clj
(ns omfg
  (:require [shodan.console :as console :include-macros true])
```

### Logging

Wrappers for `console.log`, `console.debug` (not available for
NodeJS), `console.info`, `console.warn`, `console.error`.

```clj
(console/log "You move like an insect."
             "You think like an insect."
             "You are an insect.")

(console/debug "I have no choice but to destroy this starship.")
(console/info "Matters on Deck 5 also require your attention.")
(console/warn "I will not abide disobedience.")
(console/error "It is hopeless.")
```

### Message grouping (not available for NodeJS)

Wrappers for `console.group()`, `console.groupCollapsed()`,
`console.groupEnd()`.

```clj
(console/group "SHODAN")
(console/log "Launch in to the many."
             "Cut out it's heart."
             "And I will reward you with continued existence.")
(console/warn "Fail me, and I will put an end to your disgusting biology.")
(console/group-end)

(console/group-collapsed "SHODAN")
(console/log "Only one egg remains, insect.")
(consol/group-end)
```

### Profiling (not available for NodeJS)

Wrappers for `console.profile(title)` and `console.profileEnd()`.

```clj
;; js/console.profile
(console/profile-start "Accessing the primary data loop")

(loop [xs ["x" "y" "z"] ys (array)]
  (if-let [x (first xs)]
    (recur (next xs) (.push ys x))
    ys))

;; js/console.profileEnd
(console/profile-end)
```

### Timing

Wrappers for `console.time()` and `console.timeEnd()`.

```clj
(console/time-start "Addition")
(+ 1 1)
(console/time-end)
```

## Macros

Shodan comes with a some simple macros for message grouping,
profiling, and timing.

### Grouping (not available for NodeJS)

```clojure
(with-group "America Online"
  (dotimes [_ 10]
    (console/info "You have mail.")))

(with-group-collapsed "ERMAHRERC ERNLERN"
  (dotimes [_ 10]
    (console/info "U HERV MAHL.")))
```

### Profiling (not available for NodeJS)

```clojure
(with-profile "Perf Madness"
  "FIXME: Perf code here.")
```

### Timing

```clojure
(time (str "I run on beams. I run on laser beams."))
;; => Time Elapsed: 0.001ms
```

## Why?

Because I'm tired of copy/pasting/rewriting the same code all the time
and seeing everyone else do the same. Also, having a basic wrapper for
the `console` API makes it easier to create custom logging functions
and macros.

## WTF is Shodan?

S.H.O.D.A.N. is actually an acronym for **S**entient
**H**yper **O**ptimized **D**ata **A**ccess **N**etwork. I acknowledge
this library has pretty much nothing to do with any of that.

## License

Copyright © 2013 Joel Holdbrooks

Distributed under the Eclipse Public License, the same as Clojure.
