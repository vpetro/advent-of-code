from importlib import resources

def read_data(input_filename):
    text_input = resources.open_text('resources', input_filename)
    return text_input

def down(amount, position):
    position['aim'] += amount
    return position

def up(amount, position):
    position['aim'] -= amount
    return position


def forward(amount, position):
    position['horizontal'] += amount
    position['depth'] += position['aim'] * amount
    return position

def solve(data):
    mapping = {'down': down, 'up': up, 'forward': forward}
    result = {'horizontal':0, 'depth': 0, 'aim': 0}

    for line in data:
        key, value = line.rstrip().split(' ')
        value = int(value)
        result = mapping[key](value, result)
    return result['horizontal'] * result['depth']

if __name__ == "__main__":
    data = read_data("input")
    result = solve(data)
    print(result)
