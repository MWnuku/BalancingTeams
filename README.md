# BalancingTeams
The project's goal is to create balanced teams of the same size with people using a single criteria: rate.

If the condition for creating teams of the same size can't be met the program returns TeamSizeException.

Initial team creation is based on the algorithm:

-let's say there are 8 members and 2 teams, so each team has 4 members.

--sort members by rating

--divide members into subgroups of the number of teams (in this example 2)

--for the first team take the first member from first subgroup, second from second, first from third, second from fourth

--for the second team take second member from first, first from second, ...

-then we optimize teams by searching for the best swap possible in each iteration with the following constraints:

--the difference between the rate of the members of team with the maximum average rating and the team with minimum rating must be bigger than one and smaller than difference between those two teams

--if those constraints are met these are our temporary best members but we continue to iterate through other members to see if there is a better pair

--swap this pair

--continue until there is no swap possible

Standard deviation is calculated using a traditional algorithm for the full population. 

The program returns string in the following format:

Team no 1 has 2 players (Johnny, Robbie). Average rate: 6.5

Team no 2 has 2 players (Jude, Juliet). Average rate: 6.0

Team no 3 has 2 players (Scarlet, Deborah). Average rate: 5.5

Teams rate standard deviation: 0.41

Each Member has:

String name

int rate;

Each Team has:

ArrayList<Member>

float averageRate;

int sumOfRates;

Teams calculate avgRate and sumOfRate automaticlly on addition of the member to the team.
