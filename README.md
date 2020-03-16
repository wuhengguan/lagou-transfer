## spring循环依赖
spring解决循环依赖问题：
目前spring对于单例Bean构造参数循环依赖和prototype原型的Bean循环依赖不支持，对于单例Bean通过Setter和@Autowired可以处理循环依赖
Spring的循环依赖解决理论依据是根据Java的引用传递，当获取对象引用时，执行完构造器后，即可以获取，而对象的属性值可以延后设置。
spring是通过三级缓存解决的，在执行完构造方法后，会提前暴露把自己实例化对象通过ObjectFactory提前暴露到spring容器中，即存放在一个ObjectFactory集合中，也就是所谓的三级缓存。
例如A引用B，B引用A，A在初始化过程中，执行完构造方法，把自己暴露到spring容器中，执行属性值设置，在设置引用对象B的时候，会去初始化B，然后B又引用于A，会从容器一级缓存（即已完成初始化对象集合中查找），
没查到，则会从三级缓存中查找，查找到尚未初始化完成的A对象，然后设置属性值，A初始化完成，把B迁移到二级缓存，迁移过程可以做一些操作，然后B完成初始化，A设置B的对象引用，
A继续完成初始化，把自己放到一级缓存中。
``` 
org.springframework.context.support.AbstractApplicationContext#refresh
    org.springframework.context.support.AbstractApplicationContext#finishBeanFactoryInitialization
	    org.springframework.beans.factory.support.DefaultListableBeanFactory#preInstantiateSingletons
		        org.springframework.beans.factory.support.AbstractBeanFactory#getBean
				org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean
					org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton #尝试从缓存中获取，这里分为三级缓存，其中一级是已完成初始化的，其它的是为了解决循环依赖
						org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean
							org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean #具体创建Bean的方法
								org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBeanInstance #创建bean实例，仅仅调用构造方法，尚未设置属性
								org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#addSingletonFactory  #把当前未初始化完成的对象通过ObjectFactory提前暴露到spring容器，放到三级缓存singletonFactories
								org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean    #bean属性值填充
									org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyPropertyValues #填充属性值
										org.springframework.beans.factory.support.BeanDefinitionValueResolver#resolveValueIfNecessary #处理依赖
											org.springframework.beans.factory.support.BeanDefinitionValueResolver#resolveReference	#处理依赖
												org.springframework.context.support.AbstractBeanFactory#getBean #从容器中获取依赖的Bean对象
													org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton #从三级缓存中获取A的依赖完成依赖引用
					org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#addSingleton #把初始化完成的对象放到一级缓存 
```

具体的过程时序图:
