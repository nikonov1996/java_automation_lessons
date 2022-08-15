Feature: Bot for klavagonki web site

  Background: I stay on main page
    Given Open url "https://klavogonki.ru/go?type=normal"

  Scenario: Bot start game and enters words
    When Start game
    And Wait game starting
    And Enter markered word in field by cycle
    Then See that game finished and entered symbols value more than 1500 in one minute