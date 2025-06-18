package com.woyl.my_

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.woyl.my_.ui.theme.MyApplicationTheme
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashMap


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

//    "flower","flow","flight"   "reflower","flow","flight"
    val kkk = TreeNode(5)
    val bbb = intArrayOf(2,2,1,1,1,2,2)
    val cccc = "A man, a plan, a canal: Panama"
    Log.e("mlt", ".....................${majorityElement(bbb)}")


    val lll = ListNode(1)
    lll.next = ListNode(2)
    lll.next!!.next = ListNode(3)
    lll.next!!.next!!.next = ListNode(4)
    lll.next!!.next!!.next!!.next = ListNode(5)
    lll.next!!.next!!.next!!.next!!.next = ListNode(6)
    lll.next!!.next!!.next!!.next!!.next!!.next = ListNode(7)
//    Log.e("mlt", ".....................${removeElements(lll,5)}")
//    Log.e("mlt", ".....................${isIsomorphic("paper","title")}")
    Log.e("mlt", ".....................${reverseList(lll)}")

    val num1 = 10
    val num2 = 3

    val quotient = num1 / num2
    val remainder = num1 % num2

    Log.e("mlt", "$num1 除以 $num2 的商是: $quotient")
    Log.e("mlt", "$num1 除以 $num2 的余数是: $remainder")

}

fun containsDuplicate(nums: IntArray): Boolean {
    val set = HashSet<Int>()
    for (i in nums.indices) {
        if (set.contains(nums[i])) {
            return true
        } else {
            set.add(nums[i])
        }
    }
    return false
}

fun reverseList(head: ListNode?): ListNode? {
    if (head?.next == null) {
        return head
    }
    val newHead = reverseList(head.next)
    head.next!!.next = head
    head.next = null
    return newHead
}

fun isIsomorphic(s: String, t: String): Boolean {

    if (s.length!= t.length) {
        return false
    }
    val re1 = StringBuilder()
    val re2 = StringBuilder()
    var s2t2 = HashMap<Char, Int>()
    var t2s2 = HashMap<Char, Int>()


    for (i in s.indices) {
        if (!s2t2.containsKey(s[i])) {
            s2t2[s[i]] = i+1
            re1.append("${i+1}")
        } else {
            re1.append("${s2t2[s[i]]}")
        }
    }
    for (i in t.indices) {
        if (!t2s2.containsKey(t[i])) {
            t2s2[t[i]] = i+1
            re2.append("${i+1}")
        } else {
            re2.append("${t2s2[t[i]]}")
        }
    }
    return re1.toString() == re2.toString()
}

public fun removeElements( head :ListNode?, `val` : Int) :ListNode?{
    if (head == null) {
        return head
    }
    head.next = removeElements(head.next, `val`)
    return if (head.`val` == `val`)  head.next else head
}


//fun removeElements(head: ListNode?, `val`: Int): ListNode? {
//    val dummyHead = ListNode(0)
//    dummyHead.next = head
//    var temp: ListNode? = dummyHead
//    while (temp?.next != null) {
//        if (temp.next!!.`val` == `val`) {
//            temp.next = temp.next!!.next
//        } else {
//            temp = temp.next
//        }
//    }
//    return dummyHead.next
//}

fun hammingWeight(n: Int): Int {
    var count = 0
    var num = n
    while (num != 0) {
        count += num and 1
        num = num ushr 1
    }
    return count
}

fun majorityElement(nums: IntArray): Int {
    val map = HashMap<Int, Int>()
    for (i in nums.indices) {
        if (map.containsKey(nums[i])) {
            map[nums[i]] = map[nums[i]]!! + 1
        } else {
            map[nums[i]] = 1
        }
    }
    var max = 0
    var maxKey = 0
    for (key in map.keys) {
        if (map[key]!! > max) {
            max = map[key]!!
            maxKey = key
        }
    }
    return maxKey

}

fun convertToTitle(columnNumber: Int): String {
    return ""
}

fun preorderTraversal(root: TreeNode?): List<Int> {
    val res = mutableListOf<Int>()
    preorder2(root, res)
    return res
}

fun preorder2(root: TreeNode?, res: MutableList<Int>) {
    if (root == null) {
        return
    }

    preorder2(root.left, res)
    preorder2(root.right, res)

    res.add(root.`val`)

}

fun preorder(root: TreeNode?, res: MutableList<Int>) {
    if (root == null) {
        return
    }
    res.add(root.`val`)
    preorder(root.left, res)
    preorder(root.right, res)
}

fun isPalindrome2(s: String): Boolean {
    val sgood = StringBuffer()
    val length = s.length
    for (i in 0 until length) {
        val ch = s[i]
        if (Character.isLetterOrDigit(ch)) {
            sgood.append(ch.lowercaseChar())
        }
    }
    val sgood_rev = StringBuffer(sgood).reverse()
    return sgood.toString() == sgood_rev.toString()
}

fun singleNumber(nums: IntArray): Int {
    var result = HashMap<Int, Int>()

    for (i in 0 until nums.size) {
        if (!result.containsKey(nums[i])) {
            result[nums[i]] = nums[i]
        } else {
            result.remove(nums[i])
        }
    }
    return result.keys.first()
}

fun removeNonAlphanumericAndSpaces(input: String): String {
    // 使用正则表达式替换所有非字母数字和空格的字符为空字符串
    return input.replace(Regex("[^a-zA-Z0-9]"), "")
}

fun isPalindrome(s: String): Boolean {
    val ns = removeNonAlphanumericAndSpaces(s)

    val ss = ns.lowercase(Locale.getDefault()).toList()

    if (s == " ") return true
    if (s == "") return true
    var left = 0
    var right = ss.size - 1
    while (left < right) {
        if (ss[left] != ss[right]) {
            return false
        }
        left++
        right--
    }
    return true
}

fun maxProfit(prices: IntArray): Int {
    var minprice = Int.MAX_VALUE
    var maxprofit = 0
    for (i in prices.indices) {
        if (prices[i] < minprice) {
            minprice = prices[i]
        } else if (prices[i] - minprice > maxprofit) {
            maxprofit = prices[i] - minprice
        }
    }
    return maxprofit
}

fun combination(n :Int,m:Int) :Int{
    if (m > n) {
        return 0
    }
    var numerator: Long = 1
    var denominator: Long = 1
    for (i in 1..m) {
        numerator *= (n - i + 1)
        denominator *= i.toLong()
    }
    return (numerator / denominator).toInt()
}

fun getRow(rowIndex: Int): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 0..rowIndex) {
        list.add(1)
        for (j in i - 1 downTo 1) {
            list[j] = list[j] + list[j - 1]
        }
    }
    return list
}

fun generate(numRows: Int): List<List<Int>> {
    val list = mutableListOf<List<Int>>()
    for (i in 0 until numRows) {
        val temp = mutableListOf<Int>()
        for (j in 0..i) {
            if (j == 0 || j == i) {
                temp.add(1)
            } else {
                temp.add(list[i - 1][j - 1] + list[i - 1][j])
            }
        }
        list.add(temp)
    }
    return list
}


fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null) {
        return false
    }
    if (root.left == null && root.right == null) {
        return root.`val` == targetSum
    }
    return hasPathSum(root.left, targetSum - root.`val`) || hasPathSum(root.right, targetSum - root.`val`)
}

fun isBalanced(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }

    return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1
}

fun getHeight(node: TreeNode?): Int {
    if (node == null) {
        return 0
    }
    val left = getHeight(node.left)
    val right = getHeight(node.right)
    return Math.max(left, right) + 1
}

fun minDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    if (root.left == null && root.right == null) {
        return 1
    }
    var min_dep = Integer.MAX_VALUE
    if (root.left != null) {
        min_dep = Math.min(minDepth(root.left), min_dep)
    }
    if (root.right!= null) {
        min_dep = Math.min(minDepth(root.right), min_dep)
    }
    return min_dep + 1
}

fun maxDepth(root: TreeNode?): Int {
    return checkDepth(root, 0)
}

fun checkDepth(root: TreeNode?, i: Int): Int {
    if (root == null) {
        return i
    }
    val left = checkDepth(root.left, i + 1)
    val right = checkDepth(root.right, i + 1)
    return if (left > right) {
        left
    } else {
        right
    }
}

fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    return if (p == null && q == null) {
        true
    } else if (p == null || q == null) {
        false
    } else if (p.`val` != q.`val`) {
        false
    } else {
        isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }

}

fun isSymmetric(root: TreeNode?): Boolean {
    return checkTree(root?.left, root?.right)
}

fun checkTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) {
        return true
    }
    if (p == null || q == null) {
        return false
    }
    if (p.`val` != q.`val`) {
        return false
    }
    return checkTree(p.left, q.right) && checkTree(p.right, q.left)

}


fun inorderTraversal(root: TreeNode?): List<Int> {
    val res = mutableListOf<Int>()
    inorder(root, res)
    return res
}

fun inorder(root: TreeNode?, res: MutableList<Int>) {
    if (root == null) {
        return
    }
    inorder(root.left, res)
    res.add(root.`val`)
    inorder(root.right, res)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
