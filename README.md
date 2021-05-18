# bonprix-navigation
## Herangehensweise:
Zunächst habe ich mir die grobe Architektur für die App überlegt. Theoretisch hätte es gereicht die API-Anfrage direkt in der Activity zu tätigen.
Dadurch wäre der View jedoch nicht nur für das Anzeigen der Inhalte zuständig, sondern auch für andere Aufgaben, wie den API-Zugriff.
Auch wenn dies in dieser kleinen App Arbeit erleichtern würde, habe ich mich dagegen entschieden, da in großen Projekten eine solche Trennung sinnvoll ist.
Daher habe ich das von Google viel empfohlene MVVM-Pattern verwendet um eine sinnvolle 'seperation of concerns' zu erreichen:

### View
Es gibt 2 Fragments: Ein Fragment zum Darstellen der Kategorien in einem RecyclerView und ein Fragment zum Öffnen der Website in einem WebView.
Der Pfad durch den Category-Tree wird in einer IntList gespeichert, wodurch jedes Fragment seinen individuellen Pfad und damit die Kategorien, die angzeigt werden müssen, kennt.

### ViewModel
Das ViewModel enthält die Datenstruktur, die von dem Fragment abgerufen werden kann. Zusaetzlich werden weitere informationen in dem ViewModel gespeichert, die für die Anzeige der Kategorien relevant sind.
Das ViewModel lädt automatisch die Daten über das Repository, sobald es aufgerufen wird.

### Repository
Das Repository organisiert den Zugriff auf die Daten. In der aktuellen Version lädt es nur die Daten von der API. In späteren Versionen können die geladen Daten in einer lokalen Datenbank gecached werden, sodass eine Navigation auch ohne Internetverbindung möglich ist.
Das Repository updated also die Datenbank und liefert dem ViewModel immer die aktuellen, verfügbaren Daten.

## Tests
Da ich mich bisher speziell mit UI-Tests nur sehr wenig beschäftigt habe und weil mir wegen der Masterarbeit die Zeit fehlt mich in die Thematik einzuarbeiten, habe ich mich gegen das UI-Testen entschieden.
