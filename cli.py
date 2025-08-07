# Action gain = (Total AV in Cycle(s) * Spd + Total Action Advanced) / 10000
cycles = 0 # default is calculation for 0 cycle
cycle_av = 150 + cycles * 100
speed = 0
ddd_sup = 5 # default is s5
ddd_procs = 0
eagle_procs = 0

DDD = 0.16 + 0.02*(ddd_sup - 1)
EAGLE = 0.25
advance = DDD*ddd_procs + EAGLE*eagle_procs

net_action = (cycle_av*speed + advance) / 10000