class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solut:
    def preorderTraversal(self, root):

        if root == None:
            return []

        arr = []
        def preOrdTra(node, list):
            list.append(node.val)

            if node.left:
                preOrdTra(node.left, list)
            if node.right:
                preOrdTra(node.right, list)
            return list

        return preOrdTra(root, arr)


s = Solut()
root = [1,2,3]
print(s.preorderTraversal(root))
