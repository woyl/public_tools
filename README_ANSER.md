UI显示又消失问题
1 可能是context 使用的当前的，被销毁了
Dialogfragment弹窗问题
2 在dialogfragment里面弹出软键盘 布局上移动 虚拟健高度 是设置了warp_content 问题需要设置屏幕高度方可解决问题
3 自定义tablayout + viewpager2 滑动卡顿问题，是自定义layout 里面使用了约束布局