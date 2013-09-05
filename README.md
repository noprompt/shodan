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
[shodan "0.1.0"]
```

## Usage

Console wrappers are in the `shodan.console` namespace.

```clojure
(ns omfg
  (:require [shodan.console :as console])

;;;; `console.log()`

(console/log "You have accomplished much for a thing of such small consequence.")

;; Pass a variable number of arguments.
(console/log "You move like an insect."
             "You think like an insect."
             "You are an insect.")

;;;; `console.debug()`

;; NOTE: This is not available for NodeJS.
(console/debug "I have no choice but to destroy this starship.")

;;;; `console.info()`

(console/info "Matters on Deck 5 also require your attention.")

;;;; `console.warn()`

(console/warn "I will not abide disobedience.")

;;;; `console.error()`

(console/error "It is hopeless.")

;;;; `console.group()`, `console.groupCollapsed()`, `console.groupEnd()` 

;; NOTE: These are not not available for NodeJS.
(console/group "SHODAN")
(console/log "Launch in to the many."
             "Cut out it's heart."
             "And I will reward you with continued existence.")
(console/warn "Fail me, and I will put an end to your disgusting biology.")
(console/group-end)

;;;; Collapsed message grouping
(console/group-collapsed "SHODAN")
(console/log "Only one egg remains, insect.")
(consol/group-end)

;;;; `console.assert()`

(console/assert (= 1 (+ 1 2))
                "I have suffered your company long enough.")

;;;; `console.profile()`, `console.profileEnd()`

;; NOTE: These are not not available for NodeJS.
(console/profile "Accessing the primary data loop")
(loop [xs ["x" "y" "z"] ys (array)]
  (if-let [x (first xs)]
    (recur (next xs) (.push ys x))
    ys))
(console/profile-end)

;;;; `console.time()`, `console.timeEnd()`

(console/time "Addition")
(+ 1 1)
(console/time-end)
```

## Macros

Shodan comes with a some simple macros for grouping, profiling, and
timing.

```clojure
(ns lol.core
  (:refer-clojure :exclude [time])
  (:require [shodan.console :as console])
  (:use-macros [shodan.console.macros))
```

### Grouping

```clojure
(with-group "America Online"
  (dotimes [_ 10]
    (console/info "You have mail.")))

(with-group-collapsed "ERMAHRERC ERNLERN"
  (dotimes [_ 10]
    (console/info "U HERV MAHL.")))
```

### Profiling

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
and seeing everyone else do the same.

## WTF is Shodan?

S.H.O.D.A.N. is actually an acronym for **S**entient
**H**yper **O**ptimized **D**ata **A**ccess **N**etwork. I acknowledge
this library has pretty much nothing to do with any of that.

## License

Copyright © 2013 Joel Holdbrooks

Distributed under the Eclipse Public License, the same as Clojure.
