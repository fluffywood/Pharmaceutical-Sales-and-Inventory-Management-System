<template>
  <div>
    
		<div class="card" style="margin-bottom: 5px">
			<el-input v-model="data.search.drugCode" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入药品编号"></el-input>
			<el-input v-model="data.search.drugName" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入药品名称"></el-input>
			<el-input v-model="data.search.drugSupplier" prefix-icon="Search" style="width: 240px; margin-right: 10px; margin-bottom: 3px" placeholder="请输入供应商"></el-input>
			<el-button type="info" plain @click="load">查询</el-button>
			<el-button type="warning" plain style="margin-right: 10px" @click="reset">重置</el-button>

			<el-upload v-if="data.user.role === 'admin'" :action="'http://localhost:9090/druginfo/upload?token=' + data.user.token" style="display: inline-block" :show-file-list="false" :on-success="impSuccessUpload">
				<el-button style="margin-right: 10px" type="success">批量导入</el-button>
			</el-upload>
			<el-button style="margin-right: 10px" type="info" v-if="data.user.role === 'admin'" @click="exp()">下载模板</el-button>

			<el-button style="margin-right: 10px" type="warning" v-if="data.user.role === 'admin'" @click="exp()">批量导出</el-button>
		</div>
    <div v-if="data.user.role === 'admin'" class="card" style="margin-bottom: 5px">
			<el-button v-if="data.user.role === 'admin'" type="primary" plain @click="handleAdd">新增</el-button>
			<el-button  v-if="data.user.role === 'admin'" type="danger" plain @click="delBatch">批量删除</el-button>
		</div>

    <div class="card" style="margin-bottom: 5px;">
      <el-table :data="data.tableData" strip @selection-change="handleSelectionChange">
				<el-table-column  v-if="data.user.role === 'admin'" type="selection" width="55" align="center"></el-table-column>
				<el-table-column label="药品编号" prop="drugCode"></el-table-column>
				<el-table-column label="药品名称" prop="drugName"></el-table-column>
				<el-table-column label="药品性质" prop="drugScript"></el-table-column>
				<el-table-column label="供应价格" prop="supplyPrice"></el-table-column>
				<el-table-column label="售价" prop="salePrice"></el-table-column>
				<el-table-column label="供应商" prop="drugSupplier"></el-table-column>
				<el-table-column label="保质期" prop="drugDate"></el-table-column>
        <el-table-column label="操作" align="center" width="200" fixed="right">
          <template v-slot="scope">
						<el-button v-if="data.user.role === 'admin'" type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
						<el-button v-if="data.user.role === 'admin'" type="danger" plain @click="del(scope.row.id)">删除</el-button>
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
					<el-input v-model="data.form.drugCode" placeholder="请输入药品编号"></el-input>
				</el-form-item>
				<el-form-item label="药品名称" prop="drugName">
					<el-input v-model="data.form.drugName" placeholder="请输入药品名称"></el-input>
				</el-form-item>
				<el-form-item label="药品性质" prop="drugScript">
					<el-radio-group v-model="data.form.drugScript">
						<el-radio label="处方药"></el-radio>
						<el-radio label="非处方药"></el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="供应价格" prop="supplyPrice">
					<el-select style="width: 100%" v-model="data.form.purchaseinfoId">
						<el-option v-for="item in data.purchaseinfoData" :value="item.id" :label="item.supplyPrice" :key="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="售价" prop="salePrice">
					<el-select style="width: 100%" v-model="data.form.saleinfoId">
						<el-option v-for="item in data.saleinfoData" :value="item.id" :label="item.salePrice" :key="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="供应商" prop="drugSupplier">
					<el-select style="width: 100%" v-model="data.form.supplierinfoId">
						<el-option v-for="item in data.supplierinfoData" :value="item.id" :label="item.supplierName" :key="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="保质期" prop="drugDate">
					<el-select style="width: 100%" v-model="data.form.purchaseinfoId">
						<el-option v-for="item in data.purchaseinfoData" :value="item.id" :label="item.drugDate" :key="item.id"></el-option>
					</el-select>
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
const baseApi = 'druginfo'

const data = reactive({
  user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
  tableData: [],
  total: 0,
  pageNum: 1,  // 当前的页码
  pageSize: 5,  // 每页的个数
  formVisible: false,
  form: {},
  search: {},
	ids: [],
	saleinfoData: [],
	supplierinfoData: [],
	purchaseinfoData: [],
})



// 加载表格数据
const load = () => {
	let drugCode = data.form.drugCode
	let regNum = /^[0-9]*$/
	if (drugCode && drugCode !== '') {
		if (!regNum.test(drugCode)) {
			ElMessage.error("请输入正确的数字")
			return false
		}
	}

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
	let drugCode = data.form.drugCode
	let regNum = /^[0-9]*$/
	if (drugCode && drugCode !== '') {
		if (!regNum.test(drugCode)) {
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


// 批量删除表格数据
const delBatch = () => {
	if (data.ids.length === 0) {
		ElMessage.warning('请选择数据');
		return;
	}
	ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', '删除确认', { type: 'warning' }).then(res => {
		request.delete(baseApi + "/delete/batch", {data: data.ids}).then(res => {
			if (res.code === '200') {
				ElMessage.success('批量删除成功');
				load();
			} else {
				ElMessage.error(res.msg);
			}
		})
	}).catch(err => console.log(err))
}
const handleSelectionChange = (rows) => {
	data.ids = rows.map(v => v.id)
}
// 导出
const exp = () => {
	location.href = 'http://localhost:9090/druginfo/export?token=' + data.user.token
}
const impSuccessUpload = () => {
	ElMessage.success('导入成功');
	load();
}
// 加载销售信息
const loadSaleinfo = () => {
	request.get("/saleinfo/selectAll").then(res => {
		if (res.code === '200') {
			data.saleinfoData = res.data;
		} else {
			ElMessage.error(res.msg);
		}
	})
}// 加载供应商信息
const loadSupplierinfo = () => {
	request.get("/supplierinfo/selectAll").then(res => {
		if (res.code === '200') {
			data.supplierinfoData = res.data;
		} else {
			ElMessage.error(res.msg);
		}
	})
}// 加载进货信息
const loadPurchaseinfo = () => {
	request.get("/purchaseinfo/selectAll").then(res => {
		if (res.code === '200') {
			data.purchaseinfoData = res.data;
		} else {
			ElMessage.error(res.msg);
		}
	})
}

// 加载模块数据
load()
// 加载销售信息
loadSaleinfo();// 加载供应商信息
loadSupplierinfo();// 加载进货信息
loadPurchaseinfo();

</script>
