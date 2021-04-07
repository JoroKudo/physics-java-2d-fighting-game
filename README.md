# Ultimate Arena 2D

## Abstract

Wir wollen ein 2D fighting game entwickeln, ähnlich wie zum Beispiel Street Fighter. Man sollte zu zweit lokal gegeneinander spielen können. Beide Spieler werden eine Lebensleiste haben, wer zuerst auf Null ist hat verloren. Schaden machen kann man mit einem Schlag, es gibt auch die Option Schläge zu blocken oder ihnen auszuweichen in dem man sich z.B. duckt. Man kann mit dem richtigen Timing auch kritische Treffer landen. 

## Tastenbelegung
| Funktion | Spieler 1 | Spieler 2 |
| ---------|:---------:| :--------:|
| Links    | a         | j         |
| Rechts   | d         | l         |
| Springen | w         | i         |
| Ducken   | s         | k         |
| Angriff  | e         | o         |
| Blocken  | q         | u         |

Falls die Zeit reicht, würden wir noch gerne eine Unterstützung für Controller implementieren.

## Technische Implementierung

Die Charakter wollen wir als Skellet implementieren, das heisst, dass die verschiedenen Körperteile sich bewegen können und alle eine unterschidliche Hitbox haben. Das wird uns die Kollisionserkennung eines Schlages erleichtern.

## Technologie

* Programmiersprache: Java 8 
* Graphik: JavaFX
* Physik: Dyn4j
