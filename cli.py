# Action gain = (Total AV in Cycle(s) * Spd + Total Action Advanced) / 10000


def calculate_actions(speed, cycles=0, ults=0, ddd_procs=0, ddd_sup=5):
    DDD = 0.16 + 0.02 * (ddd_sup - 1)
    EAGLE = 0.25

    cycle_av = 150 + cycles * 100
    advance = EAGLE * ults + DDD * ddd_procs

    gain = cycle_av * speed + advance
    actions = gain / 10000
    return actions


class Character:
    def __init__(self, name="None", speed=100, has_eagle=False, has_DDD=False) -> None:
        self.name = name
        self.speed = speed
        self.has_eagle = has_eagle
        self.has_DDD = has_DDD
        self.av = float(10000 / self.speed)

    def stats(self):
        info = f"Character #{self.name} with #{self.speed} speed is at #{self.av}"
        return info

    def act(self, opt=0):
        if opt == 0:
            print("Character is acting")
        self.av = self.av + float(10000 / self.speed)


def fetch_characters(file):
    with open(f"./{file}") as f:
        chars = [line.split(",") for line in f.read().split("\n")[:-1]]
    print(chars)


def main():
    fetch_characters("chars.txt")
    char_list = []
    add = True
    while add:
        name = input("Input name of character: ")
        speed = int(input("Input speed: "))
        cycles = int(input("Input target cycle count: "))
        ults = int(input("Input planned ults used (0 if not using eagle): "))
        ddd_sup = int(input("Input DDD superimpose level: "))
        ddd_procs = int(input("Input number of DDD procs: "))
        add = "Y" == input("Continue? Y/N: ")
        char = Character(name, speed, True, True)
        char_list.append(char)

    # run simulation
    # act = calculate_actions(speed, cycles, ults, ddd_procs, ddd_sup)
    run = True
    actions = [[x.name, x.av] for x in sorted(char_list, key=lambda x: x.av)]
    while run:
        for x in char_list:
            x.act()

        for act in actions:
            print("-----------------------------------")
            print("Current character: ", act[0])
            print("AV at: ", act[1])
            input("Take action? ")
            print("-----------------------------------")

        actions = [[x.name, x.av] for x in sorted(char_list, key=lambda x: x.av)]
        max_av = max([x.av for x in char_list])
        if max_av >= 150 + 100 * cycles:
            print(max_av)
            run = False


if __name__ == "__main__":
    main()
