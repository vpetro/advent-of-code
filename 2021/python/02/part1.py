from importlib import resources

def read_data(input_filename):
    text_input = resources.open_text('resources', input_filename)
    return text_input

def solve(data):
    result = {'forward':0, 'up': 0}
    for line in data:
        key, value = line.rstrip().split(' ')
        value = int(value)
        if key == "down":
            key = "up"
            value = -1 * value
        result[key] = result[key] + value
    return abs(result['up'] * result['forward'])




if __name__ == "__main__":
    data = read_data("input")
    result = solve(data)
    print(result)

