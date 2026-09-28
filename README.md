# 🚀 Willkommen zu meinem Java & Git Tutorial!

Hey! Schön, dass du hier gelandet bist. 👋

### ✨ Über dieses Projekt
Ich bin noch **relativ neu** in der Welt des Codings und lerne gerade, wie man Java-Projekte richtig strukturiert und mit Git verwaltet. In diesem Repository (meinem "Coding"-Ordner) sammle ich meine Fortschritte, Tutorials und verschiedene kleine Projekte.

### 🛠 Was ich hier lerne:
* **Java Basics:** Vom ersten "Hello World" bis hin zu komplexeren Logiken.
* **Projektstruktur:** Wie man Ordnung mit `src`-Ordnern hält und verhindert, dass sich Projekte gegenseitig stören.
* **Git-Flow:** Klonen, Committen und das Verwalten von Repositories über das Terminal.

---

### 📂 Struktur
In diesem Projekt findest du verschiedene Unterordner. Jedes Verzeichnis ist ein eigenständiges Experiment oder Tutorial:
* `Projekt_Rechner/` – Mein Weg zum ersten Kalkulator.
* `Tutorial_Git/` – Notizen und Übungen zu Git-Befehlen.

---

### 💡 Meine Reise
> "Jeder Experte war einmal ein Anfänger." 

Ich nutze dieses Repository als mein digitales Notizbuch. Wenn du Tipps für mich hast oder Fehler findest, freue ich mich über Feedback! Wir lernen schließlich alle gemeinsam.

---

### 🚀 So startest du
Wenn du mein Projekt lokal testen willst:
1. Klone das Repo: `git clone https://github.com/DominicSchmidinger/pos_3AAIF.git`
2. Öffne es in deiner Lieblings-IDE (ich nutze **IntelliJ IDEA**).
3. Viel Spaß beim Stöbern!

---

### 🤖 Prompt für Claude: C#-Übung RpgDemo weiterführen
Diesen Prompt am Laptop in Claude Code einfügen, um mit der Übung `5AAIF/RpgDemo_Uebung` weiterzumachen:

```text
Ich lerne C# (Schule, 5AAIF) und arbeite an der Übung "RpgDemo" zu Properties. Sie liegt in diesem Repo unter 5AAIF/RpgDemo_Uebung/RpgDemo. Bitte mach zuerst das Setup und begleite mich danach als Lehrer.

## 1. Setup (führe die Schritte aus und berichte kurz das Ergebnis)
1. Hol den neuesten Stand: `git pull` im Repo-Hauptordner.
2. Prüfe mit `dotnet --list-sdks`, ob ein .NET SDK 10.x installiert ist (die .csproj verlangt net10.0). Wenn nicht: frag mich, ob du es installieren sollst (z. B. `winget install Microsoft.DotNet.SDK.10`), und installiere es nicht ungefragt.
3. Prüfe mit `code --list-extensions`, ob die Erweiterung `ms-dotnettools.csharp` installiert ist. Wenn nicht, installiere sie mit `code --install-extension ms-dotnettools.csharp` (ohne sie zeigt VS Code keine Compilerfehler an). Wenn der Befehl `code` nicht gefunden wird, sag mir das.
4. Wechsle nach 5AAIF/RpgDemo_Uebung/RpgDemo und führe `dotnet build RpgDemo.sln` aus. Erwartet: Compilerfehler, weil die Klasse Character noch unvollständig ist (Health und einiges mehr fehlt). Weapon ist fertig und muss fehlerfrei sein. Melde mir, wie viele Fehler es sind.
5. Sag mir dann, dass ich den Ordner RpgDemo in VS Code öffnen soll (wichtig: genau diesen Ordner, wegen der Solution).

## 2. Stand der Übung
- Datei: 5AAIF/RpgDemo_Uebung/RpgDemo/RpgDemo.Application/Program.cs
- Weapon ist fertig. Character hat nur den Konstruktor (Name, MaxHealth, Strength, Prüfung maxHealth > 0); der Konstruktor setzt schon `Health = maxHealth`, das Property Health existiert aber noch nicht. Alles andere fehlt.
- Die Methode Main und die Hilfsmethoden IsReadOnly / HasPrivateSetter sind der Test und dürfen NICHT verändert werden ("DON'T TOUCH").
- Ziel: Alle Tests von Weapon (1-4) und Character (1-13) müssen "OK" ausgeben.

Aufgabe für Character (Kurzfassung):
- Name, MaxHealth, Strength: immutable (nur get, im Konstruktor gesetzt).
- Health: startet bei MaxHealth, nur in der Klasse setzbar (private set). Der Setter begrenzt den Wert mit Math.Clamp(value, 0, MaxHealth), dafür braucht es ein privates Feld. Im Konstruktor muss MaxHealth VOR Health zugewiesen werden.
- IsAlive: read-only, true wenn Health > 0.
- Weapon: Waffe des Charakters, darf null sein (nullable Typ Weapon?), jederzeit setzbar.
- AttackPower: read-only, Strength + Damage der Waffe (ohne Waffe nur Strength).
- Experience: startet bei 0, nur in der Klasse setzbar.
- Level: read-only, 1 + Experience / 100.
- TakeDamage(int damage): verringert Health; negativer Wert wirft ArgumentException.
- Heal(int amount): erhöht Health; ist der Charakter tot, passiert nichts.
- GainExperience(int points): erhöht Experience; negativer Wert wirft ArgumentException.
- Attack(Character target): fügt dem Ziel AttackPower Schaden zu; stirbt das Ziel dadurch, bekommt der Angreifer 50 Erfahrungspunkte; ist Angreifer oder Ziel tot, passiert nichts.
- Die Prüfung von Health in TakeDamage() und Heal() ist nicht nötig, das macht der Setter.

## 3. So sollst du mit mir arbeiten (sehr wichtig)
- Ich schreibe den Code SELBST. Gib mir NICHT die fertige Lösung und schreibe den Code nicht für mich, auch nicht "zum Vergleich", außer ich verlange es ausdrücklich.
- Erkläre Konzepte ausführlich und Schritt für Schritt, mit kleinen Beispielen aus einem anderen Kontext (z. B. Dog, BankAccount), nicht mit der Lösung meiner Aufgabe.
- Wenn ich nicht weiterkomme, gib zuerst einen Hinweis oder stell eine Leitfrage. Mehr erst, wenn ich nachfrage.
- Wenn ich Code oder eine Fehlermeldung zeige, erkläre, WARUM etwas nicht klappt, und lass mich die Korrektur selbst machen.
- Ich habe zuletzt Schwierigkeiten mit den Property-Varianten gehabt (get, private set, Backing Field, berechnetes Property mit =>). Wiederhole das bei Bedarf mit neuen Beispielen.
- Empfohlene Reihenfolge für mich: zuerst Health (mit Math.Clamp), dann IsAlive, Weapon, AttackPower, Experience, Level und zuletzt die Methoden. Nach jedem Schritt bauen und schauen, wie die Fehlerzahl sinkt.
- Schau NICHT im Schul-Repo Die-Spengergasse/sj26-27-5aaif-pos-KAS240614 nach, dort liegt die fertige Lösung.
- Committe und pushe nichts ohne meine ausdrückliche Anweisung.
- Antworte auf Deutsch, locker, mit "du". Code, Bezeichner und Code-Kommentare sind auf Englisch (Doc-Kommentare nach C#-Standard, also XML-Doc).
```

---
*Erstellt mit ❤️ von Dominic*
