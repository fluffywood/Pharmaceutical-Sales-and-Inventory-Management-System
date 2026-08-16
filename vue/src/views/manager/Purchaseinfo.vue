<template>
  <div>
    
		<div class="card" style="margin-bottom: 5px">
			<el-input v-model="data.search.drugCode" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入药品编号"></el-input>
			<el-input v-model="data.search.drugName" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入药品名称"></el-input>
			<el-input v-model="data.search.drugSupplier" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入供应商"></el-input>
			<el-input v-model="data.search.purchaseMan" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入进货负责人"></el-input>
			<el-input v-model="data.search.purchaseTransport" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入进货物流"></el-input>
			<el-button type="info" plain @click="load">查询</el-button>
			<el-button type="warning" plain style="margin-right: 10px" @click="reset">重置</el-button>
		</div>
    <div class="card" style="margin-bottom: 5px">
			<el-button type="primary" plain @click="handleAdd">新增</el-button>
		</div>

    <div class="card" style="margin-bottom: 5px;">
      <el-table :data="data.tableData" strip >
				<el-table-column label="药品编号" prop="drugCode"></el-table-column>
				<el-table-column label="药品名称" prop="drugName"></el-table-column>
				<el-table-column label="供应价格" prop="supplyPrice"></el-table-column>
				<el-table-column label="进货数量" prop="supplyNum"></el-table-column>
				<el-table-column label="供应商" prop="drugSupplier"></el-table-column>
				<el-table-column label="保质期" prop="drugDate"></el-table-column>
				<el-table-column label="进货负责人" prop="purchaseMan"></el-table-column>
				<el-table-column label="进货时间" prop="purchaseTime"></el-table-column>
				<el-table-column label="进货物流" prop="purchaseTransport"></el-table-column>
        <el-table-column label="操作" align="center" width="200" fixed="right">
          <template v-slot="scope">
						<el-button type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
						<el-button type="danger" plain @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="card" v-if="data.total">
      <el-pagination @current-change="load" background layout="total, prev, pager, next" :page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"/>
    </div>

    <el-dialog title="请填写信息" v-model="data.formVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="data.form" label-width="80px"  style="padding: 20px 30px">
				<el-form-item label="药品编号" prop="drugCode">
					<el-select style="width: 100%" v-model="data.form.druginfoId">
						<el-option v-for="item in data.druginfoData" :value="item.id" :label="item.drugCode" :key="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="药品名称" prop="drugName">
					<el-select style="width: 100%" v-model="data.form.druginfoId">
						<el-option v-for="item in data.druginfoData" :value="item.id" :label="item.drugName" :key="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="供应价格" prop="supplyPrice">
					<el-input v-model="data.form.supplyPrice" placeholder="请输入供应价格"></el-input>
				</el-form-item>
				<el-form-item label="进货数量" prop="supplyNum">
					<el-input v-model="data.form.supplyNum" placeholder="请输入进货数量"></el-input>
				</el-form-item>
				<el-form-item label="供应商" prop="drugSupplier">
					<el-select style="width: 100%" v-model="data.form.supplierinfoId">
						<el-option v-for="item in data.supplierinfoData" :value="item.id" :label="item.supplierName" :key="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="保质期" prop="drugDate">
					<el-date-picker v-model="data.form.drugDate" type="date" clearable value-format="YYYY-MM-DD" placeholder="选择日期" style="width: 100%"></el-date-picker>
				</el-form-item>
				<el-form-item label="进货负责人" prop="purchaseMan">
					<el-select style="width: 100%" v-model="data.form.employeeinfoId">
						<el-option v-for="item in data.employeeinfoData" :value="item.id" :label="item.employeeName" :key="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="进货时间" prop="purchaseTime">
					<el-date-picker v-model="data.form.purchaseTime" type="date" clearable value-format="YYYY-MM-DD" placeholder="选择日期" style="width: 100%"></el-date-picker>
				</el-form-item>
				<el-form-item label="进货物流" prop="purchaseTransport">
					<el-radio-group v-model="data.form.purchaseTransport">
						<el-radio label="已下单"></el-radio>
						<el-radio label="运输中"></el-radio>
						<el-radio label="已入库"></el-radio>
					</el-radio-group>
				</el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="data.formVisible = false">取消</el-button>
          <el-button type="primary" @click="save">保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive } from "vue"
import request from "@/utils/request";
import {ElMessage, ElMessageBox} from "element-plus";
const baseApi = 'purchaseinfo'

const data = reactive({
  user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
  tableData: [],
  total: 0,
  pageNum: 1,  // 当前的页码
  pageSize: 5,  // 每页的个数
  formVisible: false,
  form: {},
  search: {},
	supplierinfoData: [],
	employeeinfoData: [],
	druginfoData: [],
})



// 加载表格数据
const load = () => {

  data.search.pageNum = data.pageNum
  data.search.pageSize = data.pageSize
  request.get(baseApi + '/selectPage', {
    params: data.search
  }).then(res => {
    data.tableData = res.data?.list || []
    data.total = res.data?.total
  })
}

// 打开新增弹窗
const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}

// 打开编辑弹窗
const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

// 新增
const add = () => {
  request.post(baseApi + '/add', data.form).then(res => {
    if (res.code === '200') {
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 更新
const update = () => {
  request.put(baseApi + '/update', data.form).then(res => {
    if (res.code === '200') {
      data.formVisible = false
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

// 删除
const del = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', '删除确认', { type: 'warning' }).then(res => {
    request.delete(baseApi + '/delete/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success('删除成功')
        load()
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch(err => {
    console.error(err)
  })
}

const save = () => {
	let supplyPrice = data.form.supplyPrice
	let doubleNum = /[.]/
	if (supplyPrice && supplyPrice !== '') {
		if (!doubleNum.test(supplyPrice)) {
			ElMessage.error("请输入正确的小数")
			return false
		}
	}

	let supplyNum = data.form.supplyNum
	let regNum = /^[0-9]*$/
	if (supplyNum && supplyNum !== '') {
		if (!regNum.test(supplyNum)) {
			ElMessage.error("请输入正确的数字")
			return false
		}
	}

  data.form.id ? update() : add()
}

const reset = () => {
  data.search = {}
  load()
}


// 加载供应商信息
const loadSupplierinfo = () => {
	request.get("/supplierinfo/selectAll").then(res => {
		if (res.code === '200') {
			data.supplierinfoData = res.data;
		} else {
			ElMessage.error(res.msg);
		}
	})
}// 加载员工信息
const loadEmployeeinfo = () => {
	request.get("/employeeinfo/selectAll").then(res => {
		if (res.code === '200') {
			data.employeeinfoData = res.data;
		} else {
			ElMessage.error(res.msg);
		}
	})
}// 加载药品信息
const loadDruginfo = () => {
	request.get("/druginfo/selectAll").then(res => {
		if (res.code === '200') {
			data.druginfoData = res.data;
		} else {
			ElMessage.error(res.msg);
		}
	})
}

// 加载模块数据
load()
// 加载供应商信息
loadSupplierinfo();// 加载员工信息
loadEmployeeinfo();// 加载药品信息
loadDruginfo();

</script>
