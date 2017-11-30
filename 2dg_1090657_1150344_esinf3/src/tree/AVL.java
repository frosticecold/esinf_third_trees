/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author DEI-ESINF
 * @param <E>
 */
public class AVL<E extends Comparable<E>> extends BST<E> {

    private int balanceFactor(Node<E> node) {
        return height(node.getRight()) - height(node.getLeft());
    }

    private Node<E> rightRotation(Node<E> node) {
        Node<E> leftson = node.getLeft();
        node.setLeft(leftson.getRight());
        leftson.setRight(node);
        node = leftson;
        return node;
    }

    private Node<E> leftRotation(Node<E> node) {
        Node<E> rightson = node.getRight();

        node.setRight(rightson.getLeft());
        rightson.setLeft(node);

        node = rightson;

        return node;
    }

    private Node<E> twoRotations(Node<E> node) {

        if (balanceFactor(node) < 0) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        } else {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;
    }

    private Node<E> balanceNode(Node<E> node) {
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.getLeft()) <= 0) {
                node = rightRotation(node);
            } else {
                node = twoRotations(node);
            }
        } else if (balanceFactor(node) > 1) {
            if (balanceFactor(node.getRight()) >= 0) {
                node = leftRotation(node);
            } else {
                node = twoRotations(node);
            }
        }
        return node;
    }

    @Override
    public void insert(E element) {
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {
            return new Node(element, null, null);
        }

        if (node.getElement().compareTo(element) == 0) {      // replace existing element
            node.setElement(element);
        } else if (element.compareTo(node.getElement()) < 0) {  // add element to the left subtree
            node.setLeft(insert(element, node.getLeft()));
            node = balanceNode(node);
        } else {                                            // add element to the right subtree
            node.setRight(insert(element, node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }

    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, BST.Node<E> node) {
        if (node == null) {
            return null;
        }
        if (element.compareTo(node.getElement()) == 0) { // node is the Node to be removed

            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            //has two child trees
            //replace the elem with the smallest of right subtree and remove it
            E smallElem = smallestElement(node.getRight());
            node.setElement(smallElem);
            node.setRight(remove(smallElem, node.getRight()));
            node = balanceNode(node);
        } else if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(remove(element, node.getLeft()));
            node = balanceNode(node);
        } else {
            node.setRight(remove(element, node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }

    public boolean equals(AVL<E> second) {
        return equals(root, second.root);
    }

    public boolean equals(Node<E> root1, Node<E> root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public E lowestCommonAncestor(E el1, E el2) {
        return lowestCommonAncestor(root, el1, el2);
    }

    private E lowestCommonAncestor(Node<E> node, E el1, E el2) {
        if (node == null) {
            return null;
        }
        if (node.getElement().compareTo(el1) > 0 && node.getElement().compareTo(el2) > 0) {
            return lowestCommonAncestor(node.getLeft(), el1, el2);
        }
        if (node.getElement().compareTo(el1) < 0 && node.getElement().compareTo(el2) < 0) {
            return lowestCommonAncestor(node.getRight(), el1, el2);
        }
        return node.getElement();
    }

    public E search(E element) {
        Node<E> n = find(element, this.root);
        if (n == null) {
            return null;
        }
        return n.getElement();

    }
}
