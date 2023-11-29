@ApiScenarios
@HappyPathTests
Feature: Test the positive scenarios of OMDb API

  Background:
    Given Base url is "http://www.omdbapi.com/"
    And Add query parameter key and value "apikey" "<apikey>"

  @TC_002
  Scenario: Execute the api with a valid key
    Given Add query parameter key and value "t" "Dark"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type         | series    |
      | totalSeasons | 3         |
      | imdbID       | tt5753856 |
      | Title        | Dark      |
      | Response     | True      |
    And Assert status code is 200
    #And Assert json response with json file "src/test/resources/jsonFiles/TC_001.json"

  @TC_007
  Scenario: Execute api with valid movie id
    Given Add query parameter key and value "i" "tt1396484"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie     |
      | Genre    | Horror    |
      | imdbID   | tt1396484 |
      | Title    | It        |
      | Response | True      |
    And Assert status code is 200

  @TC_008
  Scenario: Execute api with valid movie title
    Given Add query parameter key and value "t" "Rocketry"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                      |
      | Genre    | Biography, Drama           |
      | imdbID   | tt9263550                  |
      | Title    | Rocketry: The Nambi Effect |
      | Response | True                       |
    And Assert status code is 200

  @TC_009
  Scenario: Execute api with valid movie id and title
    Given Add query parameter key and value "t" "Rocketry"
    And Add query parameter key and value "i" "tt9263550"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                      |
      | Genre    | Biography, Drama           |
      | imdbID   | tt9263550                  |
      | Title    | Rocketry: The Nambi Effect |
      | Year     | 2022                       |
      | Response | True                       |
    And Assert status code is 200

  @TC_013
  Scenario: Execute api with valid movie title and and type movie
    Given Add query parameter key and value "t" "Rocketry"
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

  @TC_014
  Scenario: Execute api with valid movie title and and type series
    Given Add query parameter key and value "t" "Dark"
    And Add query parameter key and value "type" "series"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type         | series    |
      | totalSeasons | 3         |
      | imdbID       | tt5753856 |
      | Title        | Dark      |
      | Response     | True      |
    And Assert status code is 200

  @TC_017
  Scenario: Execute api with valid movie id and and type series
    Given Add query parameter key and value "i" "tt5753856"
    And Add query parameter key and value "type" "series"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type         | series    |
      | totalSeasons | 3         |
      | imdbID       | tt5753856 |
      | Title        | Dark      |
      | Response     | True      |
    And Assert status code is 200

  @TC_018
  Scenario: Execute api with valid movie id and and type movie
    Given Add query parameter key and value "i" "tt9263550"
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

  @TC_025
  Scenario: Execute api with valid movie id and and movie year
    Given Add query parameter key and value "i" "tt9263550"
    And Add query parameter key and value "y" "2022"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                      |
      | Genre    | Biography, Drama           |
      | imdbID   | tt9263550                  |
      | Title    | Rocketry: The Nambi Effect |
      | Year     | 2022                       |
      | Response | True                       |
    And Assert status code is 200

  @TC_026
  Scenario: Execute api with valid movie title and and movie year
    Given Add query parameter key and value "t" "Fast & Furious 6"
    And Add query parameter key and value "y" "2013"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                    |
      | Genre    | Action, Adventure, Crime |
      | imdbID   | tt1905041                |
      | Title    | Fast & Furious 6         |
      | Year     | 2013                     |
      | Response | True                     |
    And Assert status code is 200

  @TC_029
  Scenario: Execute api with valid movie id and and short plot
    Given Add query parameter key and value "i" "tt1905041"
    And Add query parameter key and value "plot" "short"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                                                                                                                                                                            |
      | Genre    | Action, Adventure, Crime                                                                                                                                                         |
      | imdbID   | tt1905041                                                                                                                                                                        |
      | Title    | Fast & Furious 6                                                                                                                                                                 |
      | Year     | 2013                                                                                                                                                                             |
      | Plot     | Hobbs has Dominic and Brian reassemble their crew to take down a team of mercenaries: Dominic unexpectedly gets sidetracked with facing his presumed deceased girlfriend, Letty. |
      | Response | True                                                                                                                                                                             |
    And Assert status code is 200

  @TC_030
  Scenario: Execute api with valid movie id and and full plot
    Given Add query parameter key and value "i" "tt1905041"
    And Add query parameter key and value "plot" "full"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
      | Genre    | Action, Adventure, Crime                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
      | imdbID   | tt1905041                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
      | Title    | Fast & Furious 6                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
      | Year     | 2013                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
      | Plot     | Since Dom (Diesel) and Brian's (Walker) Rio heist toppled a kingpin's empire and left their crew with $100 million, our heroes have scattered across the globe. But their inability to return home and living forever on the lam have left their lives incomplete. Meanwhile, Hobbs (Johnson) has been tracking an organization of lethally skilled mercenary drivers across 12 countries, whose mastermind (Evans) is aided by a ruthless second-in-command revealed to be the love Dom thought was dead, Letty (Rodriguez). The only way to stop the criminal outfit is to outmatch them at street level, so Hobbs asks Dom to assemble his elite team in London. Payment? Full pardons for all of them so they can return home and make their families whole again. |
      | Response | True                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
    And Assert status code is 200

  @TC_031
  Scenario: Execute api with valid movie title and and short plot
    Given Add query parameter key and value "t" "Fast & Furious 6"
    And Add query parameter key and value "plot" "short"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                                                                                                                                                                            |
      | Genre    | Action, Adventure, Crime                                                                                                                                                         |
      | imdbID   | tt1905041                                                                                                                                                                        |
      | Title    | Fast & Furious 6                                                                                                                                                                 |
      | Year     | 2013                                                                                                                                                                             |
      | Plot     | Hobbs has Dominic and Brian reassemble their crew to take down a team of mercenaries: Dominic unexpectedly gets sidetracked with facing his presumed deceased girlfriend, Letty. |
      | Response | True                                                                                                                                                                             |
    And Assert status code is 200

  @TC_032
  Scenario: Execute api with valid movie title and and full plot
    Given Add query parameter key and value "t" "Fast & Furious 6"
    And Add query parameter key and value "plot" "full"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
      | Genre    | Action, Adventure, Crime                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
      | imdbID   | tt1905041                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
      | Title    | Fast & Furious 6                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
      | Year     | 2013                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
      | Plot     | Since Dom (Diesel) and Brian's (Walker) Rio heist toppled a kingpin's empire and left their crew with $100 million, our heroes have scattered across the globe. But their inability to return home and living forever on the lam have left their lives incomplete. Meanwhile, Hobbs (Johnson) has been tracking an organization of lethally skilled mercenary drivers across 12 countries, whose mastermind (Evans) is aided by a ruthless second-in-command revealed to be the love Dom thought was dead, Letty (Rodriguez). The only way to stop the criminal outfit is to outmatch them at street level, so Hobbs asks Dom to assemble his elite team in London. Payment? Full pardons for all of them so they can return home and make their families whole again. |
      | Response | True                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
    And Assert status code is 200

  @TC_033
  Scenario: Execute api with valid movie title and return data type json
    Given Add query parameter key and value "t" "Fast & Furious 6"
    And Add query parameter key and value "r" "json"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                    |
      | Genre    | Action, Adventure, Crime |
      | imdbID   | tt1905041                |
      | Title    | Fast & Furious 6         |
      | Year     | 2013                     |
      | Response | True                     |
    And Assert status code is 200

  @TC_034
  Scenario: Execute api with valid movie title and return data type xml
    Given Add query parameter key and value "t" "Fast & Furious 6"
    And Add query parameter key and value "r" "xml"
    When Execute "" resource with "get" method
    Then Assert xml response attribute using xpath
      | root.movie.@type   | movie                    |
      | root.movie.@genre  | Action, Adventure, Crime |
      | root.movie.@imdbID | tt1905041                |
      | root.movie.@title  | Fast & Furious 6         |
      | root.movie.@year   | 2013                     |
      | root.@response     | True                     |
    And Assert status code is 200

  @TC_035
  Scenario: Execute api with valid movie id and return data type json
    Given Add query parameter key and value "i" "tt1905041"
    And Add query parameter key and value "r" "json"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                    |
      | Genre    | Action, Adventure, Crime |
      | imdbID   | tt1905041                |
      | Title    | Fast & Furious 6         |
      | Year     | 2013                     |
      | Response | True                     |
    And Assert status code is 200

  @TC_036
  Scenario: Execute api with valid movie id and return data type xml
    Given Add query parameter key and value "i" "tt1905041"
    And Add query parameter key and value "r" "xml"
    When Execute "" resource with "get" method
    Then Assert xml response attribute using xpath
      | root.movie.@type   | movie                    |
      | root.movie.@genre  | Action, Adventure, Crime |
      | root.movie.@imdbID | tt1905041                |
      | root.movie.@title  | Fast & Furious 6         |
      | root.movie.@year   | 2013                     |
      | root.@response     | True                     |
    And Assert status code is 200

  @TC_037
  Scenario: Execute api with valid movie id and return data type json
    Given Add query parameter key and value "i" "tt1905041"
    And Add query parameter key and value "t" "Fast & Furious 6"
    And Add query parameter key and value "type" "movie"
    And Add query parameter key and value "y" "2013"
    And Add query parameter key and value "plot" "short"
    And Add query parameter key and value "r" "json"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | Type     | movie                                                                                                                                                                            |
      | Genre    | Action, Adventure, Crime                                                                                                                                                         |
      | imdbID   | tt1905041                                                                                                                                                                        |
      | Title    | Fast & Furious 6                                                                                                                                                                 |
      | Year     | 2013                                                                                                                                                                             |
      | Plot     | Hobbs has Dominic and Brian reassemble their crew to take down a team of mercenaries: Dominic unexpectedly gets sidetracked with facing his presumed deceased girlfriend, Letty. |
      | Response | True                                                                                                                                                                             |
    And Assert status code is 200

  @TC_041
  Scenario: Execute api with a part movie title
    Given Add query parameter key and value "s" "Nut Job"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | totalResults | 5    |
      | Response     | True |
    And Assert status code is 200
    And Assert all the movie "Title" in response contains 'Nut Job'
    And Assert all the movie "Type" in response contains 'movie'

  @TC_043
  Scenario: Execute api to search a movie by title and movie type
    Given Add query parameter key and value "s" "The Nut Job"
    And Add query parameter key and value "type" "movie"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | totalResults | 4    |
      | Response     | True |
    And Assert status code is 200
    And Assert all the movie "Title" in response contains 'The Nut Job'
    And Assert all the movie "Type" in response contains 'movie'

  @TC_044
  Scenario: Execute api to search a movie by title and movie type
    Given Add query parameter key and value "s" "The Big Bang Theory"
    And Add query parameter key and value "type" "series"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | totalResults | 2    |
      | Response     | True |
    And Assert status code is 200
    And Assert all the movie "Title" in response contains 'The Big Bang Theory'
    And Assert all the movie "Type" in response contains 'series'

  @TC_044
  Scenario: Execute api to search a movie by title and series type
    Given Add query parameter key and value "s" "The Big Bang Theory"
    And Add query parameter key and value "type" "series"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | totalResults | 2    |
      | Response     | True |
    And Assert status code is 200
    And Assert all the movie "Title" in response contains 'The Big Bang Theory'
    And Assert all the movie "Type" in response contains 'series'

  @TC_050
  Scenario: Execute api to search a series by title and year
    Given Add query parameter key and value "s" "Zero Dark Thirty"
    And Add query parameter key and value "y" "2012"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | totalResults | 1    |
      | Response     | True |
    And Assert status code is 200
    And Assert all the movie "Title" in response contains 'Zero Dark Thirty'
    And Assert all the movie "Type" in response contains 'movie'
    And Assert all the movie "Year" in response contains '2012'

  @TC_052
  Scenario: Execute api to search a series by title and json return type
    Given Add query parameter key and value "s" "Batman: The Dark Knight Returns"
    And Add query parameter key and value "r" "json"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | totalResults | 3    |
      | Response     | True |
    And Assert status code is 200
    And Assert all the movie "Title" in response contains "Batman: The Dark Knight Returns"
    And Assert all the movie "Type" in response contains "movie"

  @TC_053
  Scenario: Execute api to search a series by title and xml return type
    Given Add query parameter key and value "s" "Batman: The Dark Knight Returns"
    And Add query parameter key and value "r" "xml"
    When Execute "" resource with "get" method
    Then Assert xml response attribute using xpath
      | root.@totalResults | 3    |
      | root.@response     | True |
    And Assert status code is 200
    And Assert all the movie "root.result.@title" in xml response contains "Batman: The Dark Knight Returns"
    And Assert all the movie "root.result.@type" in xml response contains "movie"

  @TC_054
  Scenario: "Execute api to search a movie with the all details
    Given Add query parameter key and value "s" "Batman: The Dark Knight Returns"
    And Add query parameter key and value "r" "json"
    And Add query parameter key and value "y" "2012"
    And Add query parameter key and value "type" "movie"
    When Execute "" resource with "get" method
    Then Assert json response using json path
      | totalResults | 1    |
      | Response     | True |
    And Assert status code is 200
    And Assert all the movie "Title" in response contains "Batman: The Dark Knight Returns"
    And Assert all the movie "Type" in response contains "movie"





