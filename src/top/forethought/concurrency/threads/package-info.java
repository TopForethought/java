package top.forethought.concurrency.threads;
/**
 * 为什么要使用多线程?
 * 1. 更多的处理器核心:处理器核心的增多以及超线程技术,更擅长并行计算,处理器性能的提升范式,
 *                  由更多高的主频向更多的核心发展
 * 2. 更快的响应时间:处理复杂的业务逻辑,比如订单的创建,包括插入订单数据\生成订单快照\发送邮件通知
 *                   记录货品的销售数量.点击"订购" 开始,就要等待全部操作完成才能得到订购成功的结果
 *                   如何减少响应时间呢? 使用多线程技术,将数据一致性不强的操作派发给其他线程处理,也
 *                   可以使用消息队列,如生成订单快照\发送邮件等
 * 3. 更好的编程模型:java 为多线程编程提供了良好,考究并且一致的编程模型,专注于问题的解决,而不是绞尽脑汁如何多线程化
 *
 *
 *   实现线程的方式:
 *    1,继承Thread
 *    2,实现Runnable 接口
 *    3,实现Callable 接口
 *     注意:2,3 这两者实现接口并不是真正意义上的线程,只是可以被线程启动的任务,最终执行需要使用Thread.start(object)的方法
 *           启动
 */