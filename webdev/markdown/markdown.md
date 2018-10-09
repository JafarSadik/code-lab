# Mastering Markdown #
[![Waffle.io](https://img.shields.io/badge/Status-Complete-brightgreen.svg?style=flat)]()
[![Waffle.io](https://img.shields.io/badge/Helpful_%3F-A_lot-ff69b4.svg?style=flat)]()


## Badges ##
```
Make your badge:
    String rules:
        2 dashes (--): 1 dash (-)
        2 underscores (__): 1 underscore (_)
        1 space ( ) or 1 underscore (_): 1 space () 
        color: hex code or color name
    
    More parameters:
        ?style: [plastic, flat, flat-square, social]
        ?label: healthinesses (Override the default left-hand-side text)
        ?logo: data:image/png;base64... (Insert logo image (≥ 14px high))
        ?logoWidth: int (Set the horizontal space to give to the logo)
        ?link: link (Specify what clicking on the left/right of a badge should do (esp. for social badge style))
        ?colorA: hex (Set background of the left part)
        ?colorB: hex (Set background of the right part)
        ?maxAge: int (Set the HTTP cache lifetime in secs)

    Template:
    [![Waffle.io](https://img.shields.io/badge/[SUBJECT]-[STATUS]-[COLOR].svg)]()
```
```
[![Waffle.io](https://img.shields.io/badge/Status-Almost_done-yellow.svg?style=flat)]()
```

[![Waffle.io](https://img.shields.io/badge/Status-Almost_done-yellow.svg?style=flat)]()

More at [shields.io](http://shields.io)


## Text ##
```
Am I **bold** or *italic* ? <br>
Maybe I should ~~check~~ [online](google.com).
```
Am I **bold** or *italic* ? <br>
Maybe I should ~~check~~ [online](http://google.com).


## Lists ##

Numbered list :
1. One
2. Kaksi
3. Trois

Bullet points :
* Yksi
* Dos

More Points :
- Bonjour
    - hey
    - moikka
- Hola


## Images ##

![Dojo Cat](https://octodex.github.com/images/dojocat.jpg)

## Headers ##
```
# Heade 1 #
## Header 2 ##
### Header 3 ###
#### Header 4 ####
##### Header 5 #####
###### Header 6 ######
```
# Header 1 #
## Header 2 ##
### Header 3 ###
#### Header 4 ####
##### Header 5 #####
###### Header 6 ######


## Quotes ##
```
> Hey this is a quote !
> By me
```

> Hey this is a quote !
> By me


## Code ##

```python
# Let me re-iterate ...
for i in 1 .. 10 { do-something(i) }
```

or 

~~~python
import time
# Quick, count to ten!
for i in range(10):
    # (but not *too* quick)
    time.sleep(0.5)
    print(i)
~~~

or with 4 space indentation

    define foobar() {
        print "Welcome to flavor country!";
    }


## Tables ##
```
Header 1 | Header 2
---------|---------
Cell 1 | Cell 2
Cell 3 | Cell 4
```

Header 1 | Header 2
---------|---------
Cell 1 | Cell 2
Cell 3 | Cell 4


## Resources ##
- [github guide: mastering-markdown](https://guides.github.com/features/mastering-markdown/)
- [shields.io](http://shields.io)

