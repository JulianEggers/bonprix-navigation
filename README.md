# bonprix-navigation
Navigation of the bonprix website as Android app 

## Herangehensweise:
Zunächst habe ich mir die grobe Architektur für die App überlegt. Theoretisch hätte es gereicht die API-Anfrage direkt in der Activity zu tätigen.
Dadurch wäre der View jedoch nicht nur für das Anzeigen der Inhalte zuständig, sondern auch für Aufgaben, die über die Aufgaben eines einfachen Views hinausgehen.
Auch wenn dies in dieser kleinen App Arbeit erleichtern würde, habe ich mich dagegen entschieden, da in großen Projeken eine Trennung sinnvoll ist.

### View
Es gibt 2 Fragments: Ein Fragment zum darstellen der Kategorien in einem RecyclerView und ein Fragment zum öffnen der Website in einem WebView. 

### ViewModel
Das ViewModel enthält die Datenstruktur, die von den Fragments abgerufen werden kann.
Das ViewModel lädt automatisch die Daten über das Repository, sobald es aufgerufen wird.

### Repository
Das Repository managed die Daten. In der aktuellen Version lädt es nur die Daten von der API. In späteren Versionen, sollte es so ausgebaut werden,
dass die geladen Daten in einer lokalen Datenbank gecached werden, sodass eine Navigation auch ohne Internetverbindung möglich ist.
Das Repository updated also die Datenbank und liefert dem ViewModel immer die aktuellsten, verfügbaren Daten.
