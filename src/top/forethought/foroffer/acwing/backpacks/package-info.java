package top.forethought.foroffer.acwing.backpacks;
/**
 * 1. 01 背包：n 个物品，背包容量为 m, 每件物品有质量，有体积（每种物品一件） ，每种物品两种选择：选或者不选
 * <p>
 * f[i] 表示在容量为i 下能够装的最大价值
 * for(int i=0;i<n;i++){
 * for(int j=m;j>=w[i];j--){
 * f[j]=max(f[j],f[j-w[i]]+v[i]);// 选择物品i 和不选择物品i 取较大值
 * }
 * }
 * return f[m];
 * 2. 完全背包：背包容量为m, 每种物品数量不限，每种物品选择可能性： m/w[i]+1 种
 * for(int i=0;i<n;i++){ //枚举第i 个物品
 * for(int j=w[i];j<=m;j++){ //枚举 物品选择的个数 j/w[i]  j 增大，也就是说在增加背包体积
 * f[j]=max(f[j],f[j-w[i]]+v[i]);
 * }
 * }
 * 3. 多重背包，每种物品数量受约束 ，可选范围为 0-s[i]
 * for(int i=0;i<n;i++){
 * <p>
 * for(int j=m;i>=0;j--){
 * for(int k=1;k<=s[i] && k*w[i]<=m;j++){ //物品的选择0,1,2,...k
 * f[j]=max(f[j],f[j-k*w[i])+k*v[i]);
 * }
 * }
 * }
 **/