package org.activiti.designer.test;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestProcesspool1 {

	private String filename = "G://java-eclipse(mars2)/workspa/activitiDemo/src/main/resources/MyProcess.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	//创建ActivtiRule，通过实例activitiRule获取ManagementService接口实例

	@Test
	public void startProcess() throws Exception {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		//获取RepositoryService
		repositoryService.createDeployment().addInputStream("process_pool1.bpmn20.xml",
				new FileInputStream(filename)).deploy();
		//以process_pool1.bpmn20.xml作为文件部署流程
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process_pool1", variableMap);
		//启动一个流程实例
		assertNotNull(processInstance.getId());
		//验证流程实例processInstance的id属性不为空，进而证明流程启动成功。
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
	}
}