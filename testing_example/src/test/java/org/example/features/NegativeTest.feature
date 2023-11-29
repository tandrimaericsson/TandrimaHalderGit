@ApiScenarios
@NegativeTests
Feature: Test the negative scenarios of OMDb API

  Background:
    Given Base url is "http://www.omdbapi.com/"

  @TC_001
  Scenario: Execute the api with an invalid key
    Given Add query parameter key and value "apikey" "invalidKey"
    And Add query parameter key and value "t" "Dark"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Response | False            |
      | Error    | Invalid API key! |
    And Assert status code is 401

  @TC_004
  Scenario: Execute the api with an invalid title
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "t" "InvalidTitle"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Response | False            |
      | Error    | Movie not found! |
    And Assert status code is 200

  @TC_005
  Scenario: Execute the api with an invalid id
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "i" "InvalidId101"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Response | False              |
      | Error    | Incorrect IMDb ID. |
    And Assert status code is 200

  @TC_006
  Scenario Outline: Execute the api with an invalid id format
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "i" "<InvalidIMDbId>"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Response | False                                                                   |
      | Error    | Conversion from string "<InvalidIMDbId>" to type 'Double' is not valid. |
    And Assert status code is 200
    Examples:
      | InvalidIMDbId |
      | InvalidId     |
      | 10Invalid     |

  @TC_003
  Scenario: Execute the api without api key
    Given Add query parameter key and value "t" "Dark"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Response | False                |
      | Error    | No API key provided. |
    And Assert status code is 401

  @TC_010
  Scenario: Execute api with valid movie title and and an invalid id
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "t" "Rocketry: The Nambi Effect"
    And Add query parameter key and value "i" "tt92635"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Incorrect IMDb ID. |
      | Response | False              |
    And Assert status code is 200

  @TC_011
  Scenario: Execute api with invalid movie title and and a valid id
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "t" "invalidTitle"
    And Add query parameter key and value "i" "tt9263550"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Movie not found! |
      | Response | False            |
    And Assert status code is 200

  @TC_012
  Scenario: Execute api with valid movie title and and a valid id but from different set
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "i" "tt9263550"
    And Add query parameter key and value "t" "Dark"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type         | series    |
      | totalSeasons | 3         |
      | imdbID       | tt5753856 |
      | Title        | Dark      |
      | Response     | True      |
    And Assert status code is 200

  @TC_016
  Scenario: Execute api with valid movie title and and type series
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "t" "A Beautiful mind"
    And Add query parameter key and value "type" "series"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Series not found! |
      | Response | False             |
    And Assert status code is 200

  @TC_020
  Scenario: Execute api with valid movie title and and type series
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "t" "13 reason why"
    And Add query parameter key and value "type" "movie"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Movie not found! |
      | Response | False            |
    And Assert status code is 200

  @TC_022
  Scenario: Execute api with valid movie id and and type series
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "i" "tt9263550"
    And Add query parameter key and value "type" "movie"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                      |
      | Genre    | Biography, Drama           |
      | imdbID   | tt9263550                  |
      | Title    | Rocketry: The Nambi Effect |
      | Year     | 2022                       |
      | Response | True                       |
    And Assert status code is 200

  @TC_023
  Scenario: Execute api with valid series id and and type movie
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "i" "tt5753856"
    And Add query parameter key and value "type" "movie"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type         | series    |
      | totalSeasons | 3         |
      | imdbID       | tt5753856 |
      | Title        | Dark      |
      | Response     | True      |
    And Assert status code is 200

  @TC_027
  Scenario: Execute api with valid movie id and and wrong movie year
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "i" "tt9263550"
    And Add query parameter key and value "y" "1000"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                      |
      | Genre    | Biography, Drama           |
      | imdbID   | tt9263550                  |
      | Title    | Rocketry: The Nambi Effect |
      | Year     | 2022                       |
      | Response | True                       |
    And Assert status code is 200

  @TC_028
  Scenario: Execute api with valid movie title and and movie year
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "t" "Rocketry: The Nambi Effect"
    And Add query parameter key and value "y" "2000"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Movie not found! |
      | Response | False            |
    And Assert status code is 200

  @TC_038
  Scenario Outline: Execute api with valid movie title and invalid movie type
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "t" "Rocketry: The Nambi Effect"
    And Add query parameter key and value "type" "<invalidType>"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Movie not found! |
      | Response | False            |
    And Assert status code is 200
    Examples:
      | invalidType      |
      | invalidMovieType |
      | 11111111         |

  @TC_039
  Scenario Outline: Execute api with valid movie title and invalid movie plot
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "t" "Rocketry: The Nambi Effect"
    And Add query parameter key and value "plot" "<invalidType>"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                                                                                                                                                                                                                                    |
      | Genre    | Biography, Drama                                                                                                                                                                                                                         |
      | imdbID   | tt9263550                                                                                                                                                                                                                                |
      | Title    | Rocketry: The Nambi Effect                                                                                                                                                                                                               |
      | Year     | 2022                                                                                                                                                                                                                                     |
      | Plot     | Based on the life of Indian Space Research Organization scientist Nambi Narayanan, who was framed for being a spy and arrested in 1994. Though free, he continues to fight for justice against the officials who falsely implicated him. |
      | Response | True                                                                                                                                                                                                                                     |
    And Assert status code is 200
    Examples:
      | invalidType     |
      | invalidPlotType |
      | 11111111        |

  @TC_040
  Scenario Outline: Execute api with valid movie title and invalid movie plot
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "t" "Rocketry: The Nambi Effect"
    And Add query parameter key and value "r" "<invalidType>"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                      |
      | Genre    | Biography, Drama           |
      | imdbID   | tt9263550                  |
      | Title    | Rocketry: The Nambi Effect |
      | Year     | 2022                       |
      | Response | True                       |
    And Assert status code is 200
    Examples:
      | invalidType       |
      | invalidReturnType |
      | 11111111          |

  @TC_042
  Scenario: Execute api with a part movie title which is not present
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "s" "Its not a movie"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Movie not found! |
      | Response | False            |
    And Assert status code is 200


  @TC_046
  Scenario: Execute api to search a series by title where type is movie
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "s" "Night Goblin"
    And Add query parameter key and value "type" "movie"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Movie not found! |
      | Response | False            |
    And Assert status code is 200

  @TC_047
  Scenario: Execute api to search a series by title where type is episode
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "s" "Night Goblin"
    And Add query parameter key and value "type" "episode"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Movie not found! |
      | Response | False            |
    And Assert status code is 200

  @TC_048
  Scenario: Execute api to search a movie by title where type is episode
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "s" "Dark"
    And Add query parameter key and value "type" "episode"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Movie not found! |
      | Response | False            |
    And Assert status code is 200

  @TC_049
  Scenario: Execute api to search a series by title where type is invalid
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "s" "Dark"
    And Add query parameter key and value "type" "InvalidType"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Movie not found! |
      | Response | False            |
    And Assert status code is 200

  @TC_051
  Scenario: Execute api to search a series by title and wrong year
    Given Add query parameter key and value "apikey" "<apikey>"
    And Add query parameter key and value "s" "Zero Dark Thirty"
    And Add query parameter key and value "y" "2022"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Error    | Movie not found! |
      | Response | False            |
    And Assert status code is 200