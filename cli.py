# Action gain = (Total AV in Cycle(s) * Spd + Total Action Advanced) / 10000

def calculate_actions(speed, cycles=0, ults=0, ddd_procs=0, ddd_sup=5):
    DDD = 0.16 + 0.02*(ddd_sup - 1)
    EAGLE = 0.25
    
    cycle_av = 150 + cycles * 100
    advance = EAGLE*ults + DDD*ddd_procs

    gain = cycle_av*speed + advance
    actions = gain / 10000
    return actions

def main():
    speed = int(input("Input speed: "))
    cycles = int(input("Input target cycle count: "))
    ults = int(input("Input planned ults used (0 if not using eagle): "))
    ddd_sup = int(input("Input DDD superimpose level: "))
    ddd_procs = int(input("Input number of DDD procs: "))

    act = calculate_actions(speed, cycles, ults, ddd_procs, ddd_sup)
    print(act)

if __name__ == "__main__":
    main()