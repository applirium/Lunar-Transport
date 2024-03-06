# Nazov projektu: Lunar Transport
# Meno: Lukáš Štefančík AIS ID: 120891

# Opis projektu:
Moj projekt je zameraný na vytvorenie transportnej hry, kde uživateľ v pomocou rôznych špecifácií dokáže naplánovať cestu kuriera a následne spustiť.
Princíp hry spočíva v tom, že na začiatku ťahu sa vytvorí požiadavka surovín pre populačné mesto a úlohou hráča je tieto suroviny priniesť z produkčných miest, ktoré tieto suroviny vyrábjú. Hra tatkiež obsahuje banditov, ktorí sa budú náhodne po mestách pohybovať a škodiť daným mestám.
Hráč a mestá majú obmedzený inventár na ktorom môžu pracovať, taktiež má na výber z viacerých možnosti čo môže v ťahu robiť ako napr. 
V tomto štádiu sú implementované všetky funkcionality okrem rozširovania a odstraňovania miest.

Plan - naplánuje cestu, ktorú si uživateľ neskôr z možností vyberie

Action - vykoná predom naplánovanú cestu

Save Load - uloženie a načítanie dannej hry

Atď...

# Hlavné kritéria projektu

# Polymorfizmus
Pouzivam ho v informačných výpisoch o mestách a entitách a taktiež v akcii entít.

[infoEntity](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/GameFiles/GameSettings.java#L204)

[infoTown](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/GameFiles/GameSettings.java#L207)

[action](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/GameFiles/ActionSettings.java#L35)


# Dedenie
Dedenie používam, pretože mám dva typy miest, čo robia rozdielne veci a dva typy entít, ktoré sa inak správajú, ale určité elementy majú spoločné

[Production Town](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/CharacterFiles/Production_Town.java#L11)

[Population Town](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/CharacterFiles/Population_Town.java#L11)

[Player](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/CharacterFiles/Player.java#L10)

[Bandits](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/CharacterFiles/Bandits.java#L10)

# Agregacia
Agregácia je použitá v objektoch Town a Entity, kde oba objekty agregujú objekt Map_Location. Taktiež je to použité v objektoch Population_Town, Production_Town, Car, GameSettings...

[Settings](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/GameFiles/GameSettings.java#L12)

[Car](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/CharacterFiles/Player.java#L12)

[Comodity](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/CharacterFiles/Production_Town.java#L13)

[Map_Location](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/CharacterFiles/Town.java#L19)

# Oddelenie aplikačnej logiky od použivateľskej
Plne implenetovaný TUI, ktorý sprevádza uživateľa a vraví mu ako si dannú hru nastaviť a aké vstupy hra vyžduje, pričom sú ošetrené aj invalidné vstupy.

[TUI](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/GameFiles/TUI.java#L12)

# Zapuzdrenie
Všetky objekty spolu komunikujú cez príslušné gettery a settery, premenné daných objektov sú nastavené na private.

# Vedľajšie kritéria projektu

# Pouzitie navrhovych vzorov:
Vissitor - Implementované v triede Entity, metóda Action, kde dávam možnosť využívať metódu Action podľa toho, či argument je konkrétne mesto alebo list miest, ak všetky táto metóda pracuje s výberom náhodného mesta z listu miest.

Observer - Implementované pridávanie a informovanie Observerov v objekte GameSettings a následná implementácia v objekte ActionSettings, kde observer slúži na detekovanie, či boli doručené všetky suroviny, tym pádom hráča odmení odmenou, ktorú mesto ponúkalo.

[Vissitor](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/CharacterFiles/Player.java#L33)

[Observer](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/PatternFiles/ObserverDemand.java#L27)

# Serializacia
Hráč je schopný si uložiť a načítať uloženú hru -> spravené pomocou serializácie

[Serializacia](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/GameFiles/GameSettings.java#L172)

[Deserializacia](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/GameFiles/GameSettings.java#L185)

# Lambda výrazy alebo referencie na metódy
Použitie v triede ObserverDemand v metóde inform, kde vráti súčet celého inventáru

[lamba / referencie na metody](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/PatternFiles/ObserverDemand.java#L29)

# Multithredding
Použité v triede TUI na zabezpečenie chodu pri náročných operáciach

[Multithredding](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/GameFiles/TUI.java#L19)

# Použitie vhniezdených tried
V triede GameSettings mám vytvorený triedu NotEnoughMoneyExeption, ktorý sa stará o vlastnú výnimku

[Vhniezdené triedy](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/b84e676bb3a2c054ab59c93999389401adf6d227/src/GameFiles/GameSettings.java#L278)

# Ošetrenie mimoriadnych stavov prostredníctvom vlastných výnimiek:
V metóde CarUpgrade je použitá výnimka NotEnoughMoneyException, kde nedovoľuje uživateľovi vylepšiť si auto, ak nemá dostatok peňazí.

[Ošetrenie mimoriadnych stavov prostredníctvom vlastných výnimiek](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/89aefe1e519c9008e6bc6a64d96ee82f8001c5e6/src/GameFiles/GameSettings.java#L266)

# Zoznam hlavných verzií:
[Initial comit](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/3857dd846e5fca68dc3f94c1d2e35e3483efe4c5) vytovrenie CharacterFiles tried 

[1.4](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/b7db848c8bc9d271e7e9af353ed1fa639442e084) implemetnácia výpisu informácií o objektoch a operácií disatnce a slope

[1.5](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/f0125a2b25ac6c22b1fb90324d02387dcefa3b82) implemetnácia tvorenia demandListu

[1.6](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/dbc8874ce590d2e71c8bef3d9b1acd05b0a8d2b9) prvá verzia implemetnácie hlavnej logiky GameSettingsLoop

[1.10](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/bf45c1a5728a3dce5bf9601c1ac72b01917d5520) implemetnácia IO logiky

[1.15](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/940b7f1e34bc7ece1b8f0daddb895d12731fb05d) priebežná verzia projektu s implementáciou plánovania

[1.17](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/1645dc5768c8330a89197b44613875d954ae871c) refraktorizácia kódu, oddelenie aplikačnej logiky od použivateľského rozhrania, implementácie observera, vlastnej výnimky, možnosti vylepšovať auto

[1.18](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/0df6220b89f64763f6e5a9e7786a91fdfcbd3f99) implementácia serializácie a deserializácie

[1.19](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/93e39fdf3a6d1564593b8669e55a17514fd99755) implementácia celkovej vzdialenost a spotreby za naplánovanú cestu, Settings, vissitor, vnorená trieda

[1.20](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/2ca4bff45f4c2d1d4cd13513deffdee2068eb4e3) finálna refraktorizácia

[Final 1.0](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/2d0a53c7a7ab077730bd6801261c99edd17f20d5) implementácia multithreddingu, referencie na metódu

[Final 2.0](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/commit/af32c369ed04fbcf2607e23ef30d038ea85a5d0c) implementácia javadoc komentárov

# UML diagram:
UML Diagram - Hlavná logika hry je spravená v ...Settings triedach (plánovanie, akcia, manipulácia s aktuálnymi dátami), triedy o objektoch v hre sú spravené osobitne ako aj triedy týkajúce sa návhových vzorov.
 
[UML](https://github.com/OOP-FIIT/oop-2023-stv-17-a-lang-applirium/blob/d7ff557a459f73b534491dfd3a4e21b899997b06/diagram.png)
