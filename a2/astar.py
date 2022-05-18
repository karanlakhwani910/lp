class Node:
    def __init__(self, data, level, fval):
        self.data = data
        self.level = level
        self.fval = fval
    
    def generate_child(self):
        x, y = self.find(self.data)

        val_list = [[x, y+1], [x, y-1], [x-1, y], [x+1, y]];
        children = []

        for i in val_list:
            child = self.shuffle(x, y, i[0], i[1])
            if child is not None:
                childNode = Node(child, self.level+1, 0)
                children.append(childNode)
        
        return children


    def shuffle(self, x1, y1, x2, y2):

        if((x2 >= 0 & x2 < len(self.data)) & ( y2 >= 0 & y2 < len(self.data))):
            temp_puz = self.copy(self.data)
            temp = temp_puz[x1][y1]
            temp_puz[x1][y1] = temp_puz[x2][y2]
            temp_puz[x2][y2] = temp
            return temp_puz
        else:
            return None

    def copy(self, root):
        temp = []
        for i in root:
            tp = []
            for j in i:
                tp.append(j)
            temp.append(tp)
        return temp

    def find(self, puz):
        for i in range(0, len(puz)):
            for j in range(0, len(puz)):
                if(puz[i][j] == '_'):
                    return i, j
        return -1, -1


class Puzzle:
    def __init__(self, size):
        self.n = size
        self.open = []
        self.close = []
    
    def accept(self):
        puz = []
        for i in range(self.n):
            inp = input().split(" ")
            puz.append(inp)
        return puz
    

    def f(self, start, goal):
        return self.h(start.data, goal) + start.level

    def h(self, start, goal):
        count = 0

        for i in range(0, len(self.n)):
            for j in range(0, len(self.n)):
                if(start[i][j] != goal[i][j] & start[i][j] != '_'):
                    count += 1
        return count

    def process(self, start, goal):
        print("Enter the start state of matrix: ")
        start = self.accept()
        print("Enter the goal state of matrix: ")
        goal = self.accept()


        start = Node(start, 0, 0)
        start.fval = self.f(start, goal)
        

puz = [
    [1, 2, 3],
    [4, 5, '_'],
    [6, 7, 8]
]

node = Node(puz, 0, 0)
print(node.find(puz))