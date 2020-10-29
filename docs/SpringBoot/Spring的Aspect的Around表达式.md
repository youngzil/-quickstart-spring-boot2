切点函数execution()的使用  
      @Around("execution(* *(..))")  : execution()是一个切点函数,* * (..)是该函数的参数，其格式为:  
        <访问权限>? 返回值类型  包名+类名+方法名(参数类型) <throws 异常类型声明>  
    @Around("execution(* * (..))") //all  
    @Around("execution(public * * (..))")   //绑定方法的访问权限  
    @Around("execution(public * * (..) throws RuntimeException)")   //绑定异常类型声明  
    @Around("execution(public * * (..) throws RuntimeException+)")   //+代表当前类及其子类  
    @Around("execution(int * (..))")   //绑定方法的返回值  
    @Around("execution(Object+ * (..))")   //绑定方法的返回值  
    @Around("execution(void save* (..))")   //绑定方法名,以save开头的方法  
    @Around("execution(void *m* (..))")   //包含m的方法  
    @Around("execution(void com.dufy.spring.service.*.* (..))")   //绑定包名+类名+方法名  
    @Around("execution(void com.dufy.spring..*Service*.update* (..))")   //包名以com.sxt.spring开头的类名中包含Service的类中所有以update开关的方法  
    @Around("execution(void *())")   //绑定方法的参数  
    @Around("execution(void *(String))")   //绑定方法的参数  
    @Around("execution(void *(..,String,..))")   //只要有一个String类型的参数即可  
    @Around("args(int,String)")     
    @Around("execution(* save*(..)) || execution(* update*(..))")    //切点运算   (||,or,&&,and)  
    @Around("execution(* save*(..)) or execution(* update*(..))")    //切点运算  
    
    
    
    
    
    