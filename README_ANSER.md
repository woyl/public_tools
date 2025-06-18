UI显示又消失问题
1 可能是context 使用的当前的，被销毁了
Dialogfragment弹窗问题
2 在dialogfragment里面弹出软键盘 布局上移动 虚拟健高度 是设置了warp_content 问题需要设置屏幕高度方可解决问题
3 自定义tablayout + viewpager2 滑动卡顿问题，是自定义layout 里面使用了约束布局
4 -keepclasseswithmembers @kotlin.Metadata class * { *; } 混淆规则里加入这行 代码不会进行混淆


快捷键
Android Studio 的一些推荐设置，让开发更方便 https://juejin.cn/post/7420248650607099941

leetcode
URL_ADDRESShttps://leetcode.cn/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
https://leetcode.cn/problems/merge-sorted-array/solutions/666608/he-bing-liang-ge-you-xu-shu-zu-by-leetco-rrb0/