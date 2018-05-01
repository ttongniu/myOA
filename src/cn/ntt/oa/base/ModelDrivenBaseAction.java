package cn.ntt.oa.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public abstract class ModelDrivenBaseAction<T> extends BaseAction implements
		ModelDriven<T> {

	// =============== ModelDriven的支持 ==================

	protected T model;

	@SuppressWarnings("unchecked")
	public ModelDrivenBaseAction() {
		try {
			// 通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建model的实例
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

}
