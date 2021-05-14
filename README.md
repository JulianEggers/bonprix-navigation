# bonprix-navigation
## Herangehensweise:
Zunächst habe ich mir die grobe Architektur für die App überlegt. Theoretisch hätte es gereicht die API-Anfrage direkt in der Activity zu tätigen.
Dadurch wäre der View jedoch nicht nur für das Anzeigen der Inhalte zuständig, sondern auch für Aufgaben.
Auch wenn dies in dieser kleinen App Arbeit erleichtern würde, habe ich mich dagegen entschieden, da in großen Projekten eine Trennung sinnvoll ist.

### View
Es gibt 2 Fragments: Ein Fragment zum Darstellen der Kategorien in einem RecyclerView und ein Fragment zum Öffnen der Website in einem WebView.
Der Pfad durch den Category-Tree wird in einer IntList gespeichert, wodurch jedes Fragment seinen individuellen Pfad und damit die Kategorien, die angzeigt werden müssen, kennt.

### ViewModel
Das ViewModel enthält die Datenstruktur, die von dem Fragment abgerufen werden kann. Zusaetzlich werden weitere informationen in dem ViewModel gespeichert, die für die Anzeige der Kategorien relevant sind.
Das ViewModel lädt automatisch die Daten über das Repository, sobald es aufgerufen wird.

### Repository
Das Repository organisiert den Zugriff auf die Daten. In der aktuellen Version lädt es nur die Daten der API. In späteren Versionen sollte es jedoch so ausgebaut werden, dass die geladen Daten in einer lokalen Datenbank gecached werden, sodass eine Navigation auch ohne Internetverbindung möglich ist.
Das Repository updated also die Datenbank und liefert dem ViewModel immer die aktuellen, verfügbaren Daten.

## Tests
In meinen bisherigen (privaten) Projekten habe ich keine Testfälle geschrieben, weil ich oft große Änderungen vorgenommen habe, wodurch sich die Testfälle zu oft geändert hätten. Um diesen Overhead zu vermeiden, habe ich das Testen immer manuell durchgeführt. Ich denke, dass dieses Vorgehen für kleine und private Projekte als eine legitime Lösung der Qualitätssicherung gesehen werden kann. Für große Projekte ist dies natürlich keine Option. 
Da ich mich speziell mit UI-Tests bisher nur sehr wenig beschäftigt habe und mich hierfür in die Thematik reinarbeiten müsste, und weil ich gerade wegen der Masterarbeit sehr viel um die Ohren habe, habe ich die UI-Tests zunächst weggelassen. Speziell für das Fragment, welches den RecyclerView zeigt, ist es aber angebracht UI-Tests zu schreiben.
