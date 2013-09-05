# Shodan

A ClojureScript library providing wrappers for the JavaScript
`console` API.

## Basic usage

Require the `shodan.console` namespace.

```clojure
(ns omfg
  (:require [shodan.console :as console])
```

### `console.log`

```clojure
(console/log "You have accomplished much for a thing of such small consequence.")
;; Pass a variable number of arguments.
(console/log "You move like an insect."
             "You think like an insect."
             "You are an insect.")
```

### `console.debug` (not available for NodeJS)

```clojure
(console/debug "I have no choice but to destroy this starship." 
```

### `console.info`

```clojure
(console/info "Matters on Deck 5 also require your attention.")
```

### `console.warn`

```clojure
(console/warn "I will not abide disobedience.")
```

### `console.error`

```clojure
(console/error "It is hopeless.")
```

### `console.group`, `console.groupCollapsed`, `console.groupEnd` 

**Note:** Not available for NodeJS.

```clojure
(console/group "SHODAN")
(console/log "Launch in to the many."
             "Cut out it's heart."
             "And I will reward you with continued existence.")
(console/warn "Fail me, and I will put an end"
              "to your disgusting biology")
(console/group-end)

;; Collapsed message grouping

(console/group-collapsed "SHODAN")
(console/log "Only one egg remains, insect."
(consol/group-end)
```

### `console.assert`

```clojure
(console/assert (= 1 (+ 1 2))
                "I have suffered your company long enough.")
```

### `console.profile`, `console.profileEnd()`

**Note:** Not available for NodeJS.

```clojure
(console/profile "Accessing the primary data loop")
(loop [xs ["x" "y" "z"] ys (array)]
  (if-let [x (first xs)]
    (recur (next xs) (.push ys x))
    ys))
(console/profile-end)
```

### `console.time`, `console.timeEdn`

```clojure
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
**H**yper-**O**ptimized **D**ata **A**ccess **N**etwork. I acknowledge
this library has pretty much nothing to do with any of that.

## License

Copyright © 2013 Joel Holdbrooks

Distributed under the Eclipse Public License, the same as Clojure.
