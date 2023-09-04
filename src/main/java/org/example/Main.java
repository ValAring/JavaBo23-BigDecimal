package org.example;
/*
Erstellt eine Record 'Client' mit den Eigenschaften Vorname, Nachname und Kundennummer (wähle geeignete englische Feldnamen).

Erstellt eine Klasse 'Account' (kein Record, soll vorerst veränderlich sein) mit den Eigenschaften Kontonummer (String),
Kontostand (BigDecimal) und dem zugehörigen Kunden.

Implementiert eine Methode, um Geld auf das Konto einzuzahlen.

Implementiert eine Methode, um Geld vom Konto abzuheben.

Erstellt eine Klasse 'BankService', die eine Menge von Konten verwaltet.

Implementiert eine Methode im BankService, um ein Konto zu eröffnen. Man soll dafür nur einen Kunden als Argument übergeben
müssen. Es soll die neue Kontonummer zurückgeben.

Implementiert eine Methode im BankService, von einer Kontonummer (als String) einen bestimmten Betrag (als BigDecimal)
auf eine andere Kontonummer (als String) zu überweisen.

Baut Eure Records und Klassen so um, dass ein Konto mehreren Kontoinhaberinnen gehören kann (zwei oder mehreren Kontoinhaberinnen).

Schreibe im Service eine Methode public List<String> split(String accountNumber), die ein Konto zu gleichen Teilen aufteilt.
Aus einem Gemeinschaftskonto soll dabei pro Kontoinhaber*in ein Einzelkonto entstehen. Es soll die entstandenen neuen Kontonummern zurückgeben. Jedes Konto soll nach der Aufteilung gleich viel Geld abbekommen (+- 1 Cent). Achte darauf, dass uns als Bank dabei weder Cent-Gewinne noch Cent-Verluste entstehen.

PS: wie üblich gibt es auch bei unserer Bank keine halben Cent ;)

Tipp: auch hier eignet sich Test Driven Development sehr gut, um die Aufgabe zu lösen! (gilt auch für die folgenden Aufgaben)

Erweitert den 'BankService' um eine Methode, die Zinsen für alle Kundenkonten basierend auf einem Zinssatz berechnet und
gutschreibt. (Zinsen = Kontostand * (Zinssatz / 100).

Schreibt Eure Klassen so um, dass es nicht mehr einen fixen Kontostand gibt, sondern eine Liste von Transaktionen.
Jede Transaktion hat einen Betrag, einen Saldo (neuer Kontostand nach der Veränderung), eine Beschreibung (optional) und ein Datum. Die Transaktionen sollen als Record implementiert werden. Um den aktuellen Kontostand zu ermitteln soll der BankService das Saldo der letzten Tran

Bei jeder Abheben- oder Einzahlen-Aktion sollen die Zinsen, die seit der letzten Transaktion angefallen sind berechnet werden.
Der im BankService hinterlegte Zinssatz soll dabei den jährlichen Zins darstellen. Achtet bei der Berechnung auf den
Zinseszinseffekt (bei 4% Zins sind nach einem halben Jahr noch nicht ganz 2% angefallen). Erstellt für Zins-Gutschriften
(oder Zins-Belastungen) jeweils pro Transaktion eine zweite Transaktion.
*/

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        Client human1 = new Client(100001, "Rick", "Sanchez");
        //Account acc1 = new Account("DE123456", new BigDecimal(540.01), human1);
        Client human2 = new Client(100002, "Jerry", "Smith");
        //Account acc2 = new Account("DE234567", new BigDecimal(40.02), human2);
        Client human3 = new Client(100003, "Beth", "Smith");
        /*
        System.out.println("Rick's current: " + acc1.getBalance());
        acc1.depositMoney(new BigDecimal(60.99));
        System.out.println("Rick's new deposit: " + acc1.getBalance());

        System.out.println("Jerry's current: "+acc2.getBalance());
        acc2.withdrawMoney(new BigDecimal(60.52));
        System.out.println("Jerry's new deposit: "+acc2.getBalance());
        */
        BankService spassKasse = new BankService();
        String bethKontoNR = spassKasse.newAccount(human3);
        System.out.println("Beth KontoNR: "+ bethKontoNR);

        String rickKontoNR = spassKasse.newAccount(human1);
        String jerryKontoNR = spassKasse.newAccount(human2);

        Account ricksAccount = spassKasse.findAccount(rickKontoNR);
        ricksAccount.depositMoney(new BigDecimal(1000));

        System.out.println("Rick's current: " + ricksAccount.getBalance());
        System.out.println("Beth's current: " + spassKasse.findAccount(bethKontoNR).getBalance());

        spassKasse.transferMoney(rickKontoNR, bethKontoNR, new BigDecimal(500));

        System.out.println("Rick's current: " + ricksAccount.getBalance());
        System.out.println("Beth's current: " + spassKasse.findAccount(bethKontoNR).getBalance());
    }
}