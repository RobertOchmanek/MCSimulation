- program, który generuje stan będący w równowadze termodynamicznej w danej temperaturze

    - metoda Monte-Carlo bazuje na losowych zmianach ułożenia magnesów / sprowadza się do wprowadzenia małej i losowej zmiany stanu układu

        - to co rozumiemy przez małą zmianę zależy w dużej mierze od temperatury
        - może to oznaczać np. zmianę kierunku ustawienia jednego losowo wybranego magnesu z X na X+1 lub X-1
        - mechanika sprawdza się do tego, że nie wszystkie zamiany wprowadzane są z identycznym prawdopodobieństwem

            - prawdopodobieństwo obniżenia energii układu jest większe od jej podniesienia

                - jeśli temperatury są niskie, to często akceptowane będą wyłączenie zmiany energii prowadzące do jej obniżenia i tylko sporadycznie do jej wzrostu
                - w wyższych temperaturach szanse na podniesienie energii układu będą rosły

            - dla prawopodobieństwa zmiany stosowane są następujące dwie formuły:

                - równanie Metropolisa
                - równanie Glaubera

            - typowa sztuczka sprowadza się do monitorowania częstości z jaką akceptujemy zmiany

                - jeśli są one akceptowane bardzo często (ponad 50% przypadków), to w jednym kroku Monte-Carlo możemy mocniej zmieniać system, np. obracając magnesy o większy kąt lub wiele magnesów zamiast jednego
                - jeśli zmiany zachodzą sporadycznie i wiele kroków prowadzi tak naprawdę do przywrócenia stanu sprzed zmiany modyfikacje stanu muszą być subtelniejsze

        - następnie ustalamy jaką zmianę energii układu zmiana ta spowoduje, o ile zostanie zatwierdzona

            - w zależności od tego jak duża zmiana jest realizowana możliwe są dwa skrajne podejścia do wyznaczenia energii układu:

                - dla zmian, w których bierze udział duża liczba magnesów różnica energii spowodowana zmianą musi być policzona jako różnica energii całkowitych układu wyznaczonych po zmianie i przed
                - dla zmian obejmujących zmianę kierunku jednego magnesu wystarczy wyliczyć energię oddziaływania magnesu, którego kierunek zmieniamy po i przed zmianą

            - aby policzyć energię oddziaływania magnesów potrzebujemy jedynie ich ustawienia (kierunku) i pewnych stałych

                - kierunek magnesu będzie zapisany jako liczba całkowita z ograniczoną liczbą różnych wartości, np. 2 (tylko góra-dół, czyli tzw. model Isinga), 3, 5, 10 itd.
                - kąty mierzymy względem dodatniej części osi X
                - należy przeliczyć kąt zapisany jako liczba całkowita na kąt w radianach

- stan równowagi został osiągnięty jeśli:

    - czekamy długo i zakładamy, że tak się stało
    - liczymy średnią jakiegoś parametru w trakcie pewnej liczby kroków symulacji, a potem rachunek powtarzamy, jeśli wyszło (mniej-więcej) to samo, to stan równowagi został osiągnięty:

        - w trakcie symulacji obserwowane są zmiany energii całkowitej układu, parametru porządku oraz uporządkowania najbliższych sąsiadów

- należy zastosować tzw. periodyczne warunki brzegowe

    - prowadzą one do swoistego "zapętlenia" przestrzeni naszego systemu - np. wychodząc z prawej strony wchodzimy jednocześnie do niego z lewej, wychodząc w dół wchodzimy z góry

- magnesy oddziałują ze sobą wzajemnie oraz z zewnętrznym polem

    - zakładamy, że wszystkie magnesy są takie same
    - oddziaływanie może ulegać zmianie wraz z odległością pomiędzy magnesami