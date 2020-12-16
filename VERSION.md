# Spring Cloud 版本说明
`SNAPSHOT` 快照版，可以稳定使用，且仍在继续改进版本。

`M(Milestone)` 里程碑版本，在版发布之前 会出几个里程碑的版本。

`PRE(preview edition)` 预览版,内部测试版. 主要是给开发人员和测试人员测试和找BUG用的，不建议使用；

`RC(Release Candidate)` 发行候选版本，基本不再加入新的功能，主要修复bug。是最终发布成正式版的前一个版本，将bug修改完就可以发布成正式版了。

`SR(Service Release)` 修正版或更新版，修正了release版本推出后发现的Bug。

`GA(General Availability)` 正式发布的版本，官方开始推荐广泛使用，国外有的用GA来表示release版本。

#	英文			| 中文		| 终结版本	| boot大版本	| boot代表	| 说明
#	Angel		|  安吉尔		| SR6		| 1.2.X			| 1.2.8		| GA
#	Brixton		|  布里克斯顿	| SR7		| 1.3.X			| 1.3.8		| GA
#	Camden		|  卡梅登		| SR7		| 1.4.X			| 1.4.2		| GA
#	Dalston		|  达斯顿		| SR5		| 1.5.X			| *			| GA
#	Edgware		|  艾奇韦尔	| SR5		| 1.5.X			| 1.5.19	| GA
#	Finchley	|  芬奇利		| SR2		| 2.0.X			| 2.0.8	GA
#	Greenwich	|  格林威治	| SR6		| 2.1.X			| 2.1.2	GA
#	Hoxton		|  霍克斯顿	| SR8		| 2.2.X	, 2.3.x(Starting with SR5)	| GA
Spring Boot版本格式：`主版本号`--`子版本号`--`修正版本号`--`软件版本阶段`

Spring Cloud版本阶段：为了记录版本的各个阶段，开发阶段（snapshot） -  里程碑阶段（MX） - 发布候选（RCX）- 正式版本（release） - 修正版本（SRX）;

版本标识：为了方便开发使用者群体选择合适的版本，也说明了当前版本号处于一个什么情况了，常见的就是 `SNAPSHOT` / `PRE` / `GA` / `CURRENT`。