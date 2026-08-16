<template>
  <div>
    
		<div class="card" style="margin-bottom: 5px">
			<el-input v-model="data.search.drugCode" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入药品编号"></el-input>
			<el-input v-model="data.search.drugName" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入药品名称"></el-input>
			<el-input v-model="data.search.returnApply" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入退货申请"></el-input>
			<el-input v-model="data.search.returnPos" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入操作状态"></el-input>
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
				<el-table-column label="售价" prop="salePrice"></el-table-column>
				<el-table-column label="销售数量" prop="saleNum"></el-table-column>
				<el-table-column label="退货申请" prop="returnApply"></el-table-column>
				<el-table-column label="操作状态" prop="returnPos"></el-table-column>
				<el-table-column label="退货物流" prop="returnTransport"></el-table-column>
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
				<el-form-item label="售价" prop="salePrice">
					<el-input v-model="data.form.salePrice" placeholder="请输入售价"></el-input>
				</el-form-item>
				<el-form-item label="销售数量" prop="saleNum">
					<el-input v-model="data.form.saleNum" placeholder="请输入销售数量"></el-input>
				</el-form-item>
				<el-form-item label="退货申请" prop="returnApply">
					<el-radio-group v-model="data.form.returnApply">
						<el-radio label="y"></el-radio>
						<el-radio label="n"></el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="操作状态" prop="returnPos">
					<el-radio-group v-model="data.form.returnPos">
						<el-radio label="同意"></el-radio>
						<el-radio label="拒绝"></el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="退货物流" prop="returnTransport">
					<el-radio-group v-model="data.form.returnTransport">
						<el-radio label="退货下单"></el-radio>
						<el-radio label="运输中"></el-radio>
						<el-radio label="已返回"></el-radio>
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
const baseApi = 'saleinfo'

const data = reactive({
  user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
  tableData: [],
  total: 0,
  pageNum: 1,  // 当前的页码
  pageSize: 5,  // 每页的个数
  formVisible: false,
  form: {},
  search: {},
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
	let salePrice = data.form.salePrice
	let doubleNum = /[.]/
	if (salePrice && salePrice !== '') {
		if (!doubleNum.test(salePrice)) {
			ElMessage.error("请输入正确的小数")
			return false
		}
	}

	let saleNum = data.form.saleNum
	let regNum = /^[0-9]*$/
	if (saleNum && saleNum !== '') {
		if (!regNum.test(saleNum)) {
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


// 加载药品信息
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
// 加载药品信息
loadDruginfo();

</script>
