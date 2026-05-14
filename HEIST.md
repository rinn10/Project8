# HEIST.md

The hidden note in `example-dataset.data` redirected us to the “Breaking In” activity.
The activity explains that the MathLAN network changes between five graph configurations:
- mathlan.A.data
- mathlan.B.data
- mathlan.C.data
- mathlan.D.data
- mathlan.E.data
along with a file listing all machines:
- mathlan-machines.txt

The goal is to:
1. Find the machine that is unreachable across all configurations.
2. Find the configuration that allows the network to fully connect.
3. Use the machine name and configuration letter together as the password.

To solve this part, we wrote a driver in `Main.java` that read each MathLAN configuration file and built an adjacency-list graph from the machine connections. We then ran BFS starting from every machine and kept the traversal that reached the largest number of nodes. By comparing the reachable machines against the complete machine list from `mathlan-machines.txt`, we identified which machines were disconnected in each configuration.

Configuration D was the only configuration where BFS reached all 78 machines, so it is the fully connected network configuration. Salton repeatedly appeared disconnected in multiple configurations, making it the target computer.

## Answer

Target computer: Salton
Good configuration: D
Password: SaltonD