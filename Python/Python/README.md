# Python-Operation-MySQL-Database-
两个项目：
	Python-01：Python操作MySQL数据库
	myblog：Python 开发建议博客系统

GitHub使用流程：
	1、注册Github账号

	2、安装GitHub for Windows的一个客户端，并进行登陆

	3、在GitHub官网上创建一个仓库

	4、将仓库检出到本地目录：git clone 仓库地址

	5、可以在检出目录中添加或修改文件

	6、提交
		git status #查看工作区的状态，检测有没有跟踪文件(修改)
		git add <file> #建立一个跟踪
		git status #再次查看工作区状态  git reset HEAD <file> 将文件撤出暂存区
		git commit <file> #提交，会提示添加评论(提交到本地，还没有到远程服务器)
		git status #再次查看工作区状态，
		git push #提交到远程服务器

	7、Github for Windows使用
		从远程服务器clone一个项目
		修改项目（GitHub for windows 自动检测是否被修改）
		Github for Windows的Changs菜单会有提示有没有文件被修改
		填写Summary和Description就可以提交到本地
		点击主页中的Fetch origin就可以提交到GitHub远程服务器

	8、解决冲突
		首先将服务器最新的项目pull下载解决冲突
		git diff #查看冲突，自己决定冲突的取舍
		再次提交就可以张长提交
		其他人在pull就是解决冲突后的最新文件

	9、回滚到之前版本
		git log #查看提交日志
		找到相应的commit 后边一串的id
		git reset --hard <commit id>

	10、建立里程碑
		进入GitHub的官网，进入相应的项目，点击releases
			填写 
				Tag version 版本号
				Release title 发布标题
				Write Describe this release 描述
				还有一个Preview(之前的版本)
			Publish release 发布这个版本
			Save draft 保存草稿(保存但不发布)

	11、分支开发和合并分支
		在GitHub for Windows或GitHub官网创建一个分支
		可以在GitHub for Windows切换分支(git checkout develop #在master分支执行)，一般是master和develop分支
		git merge develop #在master分支执行

	12、多人合作的一些经验
		多用客户端和工具，少用命令行，除非是Linux服务器上面直接开发
		每次提交钱，diff自己的代码，以免提交错误的代码
		下班回家钱，整理好自己的工作区
		并行的项目，只用分支开发
		遇到冲突是，搞明白冲突的原因，千万不要随机丢弃别人的代码
		产品发布后，记得打tag，方便将来拉分支修改bug