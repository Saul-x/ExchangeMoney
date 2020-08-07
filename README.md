# ExchangeMoney

本题第一问是一个需要流露找零细节的找零问题
总体思路是动态规划

货币币值：k1,k2...kn
对于找零函数F和总值total，F(total)总是能得到满足于总值total的最少的货币张数
那么可以推论：


F(total) = min{F(total-k1),F(total-k2)....F(total-kn)) + 1; (这里total - kn必须大于0，否则不纳入min计算)


F(0) = 0;

这样，我们可以有一张total大小的表，依次计算并记录F(0)....F(total-1)的解，这样重复的子问题不需要再次计算，只需要查表中结果就好了，从而可以得到F(total)的解

因为本题要求不止是最少的货币张数，而是找零细节（106人民币=  xx欧元 +  xx美元 +  xx人民币），所以这边用了ExchangeSolution作为F函数的解的对应类, 其中包含Map<Currency, Integer>来存储找零的细节，key值是货币种类，value值是对应的张数。

ExchangeSolution也是找零问题的具体业务类，用户只能通过ExchangeSolution.exchange(int num, Currency unit)，提供对应的货币种类和其数目，计算出一个ExchangeSolution。


CurrenciesExchangeUtils中
CurrenciesExchangeUtils.exchangeMoneyWithLeastCurrencies
CurrenciesExchangeUtils.exchangeMoneyWithMostCurrencies
分别是两问的接口函数。

tip:
本实例中的动态规划子问题结果集都是保存在java内存中的，数据量比较大时容易爆内存，而且每次执行exchange重新计算也并不是一个好方式，容易频繁触发gc，真正的业务场景中这个子问题结果集存放可能有很多的不同的方式（依据具体的业务而定）。
